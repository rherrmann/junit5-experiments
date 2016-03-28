package com.codeaffine.junit5;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.gen5.api.extension.AfterEachExtensionPoint;
import org.junit.gen5.api.extension.ExtensionContext;
import org.junit.gen5.api.extension.InstancePostProcessor;
import org.junit.gen5.api.extension.MethodInvocationContext;
import org.junit.gen5.api.extension.MethodParameterResolver;
import org.junit.gen5.api.extension.TestExtensionContext;

public class TemporaryFolderExtension
  implements AfterEachExtensionPoint, InstancePostProcessor, MethodParameterResolver
{

  private final Collection<TemporaryFolder> tempFolders;
  
  public TemporaryFolderExtension() {
    tempFolders = new ArrayList<>();
  }
  
  @Override
  public void afterEach( TestExtensionContext context ) throws IOException {
    tempFolders.forEach( TemporaryFolder::cleanUp );
  }

  @Override
  public void postProcessTestInstance( TestExtensionContext context ) {
    Object testInstance = context.getTestInstance();
    stream( testInstance.getClass().getDeclaredFields() )
      .filter( field -> field.getType() == TemporaryFolder.class )
      .forEach( field -> injectTemporaryFolder( testInstance, field ) );
  }

  private void injectTemporaryFolder( Object instance, Field field ) {
    field.setAccessible( true );
    try {
      field.set( instance, createTempFolder() );
    } catch( IllegalAccessException iae ) {
      throw new RuntimeException( iae );
    }
  }

  @Override
  public boolean supports( Parameter parameter,
                           MethodInvocationContext methodInvocationContext,
                           ExtensionContext extensionContext ) 
  {
    return parameter.getType() == TemporaryFolder.class;
  }

  @Override
  public Object resolve( Parameter parameter,
                         MethodInvocationContext methodInvocationContext,
                         ExtensionContext extensionContext ) 
  {
    return createTempFolder();
  }

  private TemporaryFolder createTempFolder() {
    TemporaryFolder result = new TemporaryFolder();
    result.prepare();
    tempFolders.add( result );
    return result;
  }

}

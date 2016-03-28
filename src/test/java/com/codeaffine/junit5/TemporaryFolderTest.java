package com.codeaffine.junit5;

import java.io.File;
import java.io.IOException;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderTest {
  
  TemporaryFolder tempFolderField;

  @Test
  public void useTempFolder( TemporaryFolder tempFolderParameter ) throws IOException {
    File file1 = tempFolderField.newFile( "foo" );
    File file2 = tempFolderParameter.newFile( "foo" );
    
    Assertions.assertNotSame( tempFolderField, tempFolderParameter );
    Assertions.assertTrue( file1.exists() );
    Assertions.assertTrue( file2.exists() );
    Assertions.assertNotEquals( file1, file2 );
  }
}

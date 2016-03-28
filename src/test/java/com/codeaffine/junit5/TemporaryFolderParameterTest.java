package com.codeaffine.junit5;

import java.io.File;
import java.io.IOException;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;

public class TemporaryFolderParameterTest {
  
  @Test
  @ExtendWith(TemporaryFolderExtension.class)
  void useTempFolderParamerter( TemporaryFolder tempFolder ) throws IOException {
    File file = tempFolder.newFile( "foo" );
    
    Assertions.assertTrue( file.exists() );
  }
  
}

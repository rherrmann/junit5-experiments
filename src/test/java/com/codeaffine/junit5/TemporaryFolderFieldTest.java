package com.codeaffine.junit5;

import java.io.File;
import java.io.IOException;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.ExtendWith;

@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderFieldTest {
  
  TemporaryFolder tempFolderField;

  @Test
  public void useTempFolderField() throws IOException {
    File file = tempFolderField.newFile( "foo" );
    
    Assertions.assertTrue( file.exists() );
  }
}

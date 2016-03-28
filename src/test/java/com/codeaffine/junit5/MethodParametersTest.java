package com.codeaffine.junit5;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInfo;

public class MethodParametersTest {
 
  @Test
  public void testWithBuiltInResolver( TestInfo testInfo ) {
    Assertions.assertNotNull( testInfo );
  }
  
  @Test
  void testWithoutMatchingResolver( int param ) {
    Assertions.assertTrue( true );
  }
}

package com.codeaffine.junit5;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;

public class DisabledWhenIntegrationTest {

  class MemberClassCondition implements DisabledWhenCondition {
    @Override
    public boolean isSatisfied() {
      return true;
    }
  }

  @Test
  @DisabledWhen(AlwaysDisabled.class)
  public void shouldNeverBeExecuted() {
    Assertions.fail( "Should not be executed" );
  }
  
  @Test
  @DisabledWhen(MemberClassCondition.class)
  public void shouldAlsoNeverBeExecuted() {
    Assertions.fail( "Should not be executed" );
  }
}

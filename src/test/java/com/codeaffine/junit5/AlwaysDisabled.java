package com.codeaffine.junit5;

public class AlwaysDisabled implements DisabledWhenCondition {

  @Override
  public boolean isSatisfied() {
    return true;
  }
}
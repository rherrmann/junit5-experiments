package com.codeaffine.junit5;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.gen5.api.extension.ExtendWith;

@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(DisabledWhenExtension.class)
public @interface DisabledWhen {

  Class<? extends DisabledWhenCondition> value();
}

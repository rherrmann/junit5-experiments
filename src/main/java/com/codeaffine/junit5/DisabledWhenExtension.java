package com.codeaffine.junit5;

import static org.junit.gen5.commons.util.AnnotationUtils.findAnnotation;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.util.Optional;

import org.junit.gen5.api.extension.ConditionEvaluationResult;
import org.junit.gen5.api.extension.TestExecutionCondition;
import org.junit.gen5.api.extension.TestExtensionContext;

public class DisabledWhenExtension implements TestExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluate( TestExtensionContext context ) {
    ConditionEvaluationResult result = ConditionEvaluationResult.enabled( "" );
    DisabledWhenCondition disabledCondition = getDisabledWhenCondition( context.getElement(), context.getTestInstance() );
    if( disabledCondition.isSatisfied() ) {
      result = ConditionEvaluationResult.disabled( "Disabled by @DisabledWhen" );
    }
    return result;
  }

  private DisabledWhenCondition getDisabledWhenCondition( AnnotatedElement element, Object target ) {
    Optional<DisabledWhen> annotation = findAnnotation( element, DisabledWhen.class );
    if( annotation.isPresent() ) {
      try {
        Class<? extends DisabledWhenCondition> conditionType = annotation.get().value();
        if( isConditionTypeStandalone( conditionType ) ) {
          return conditionType.newInstance();
        } else {
          return conditionType.getDeclaredConstructor( target.getClass() ).newInstance( target );
        }
      } catch( ReflectiveOperationException | SecurityException e ) {
        throw new RuntimeException( e );
      }
    }
    return () -> false;
  }

  private boolean isConditionTypeStandalone( Class<?> conditionType ) {
    return !conditionType.isMemberClass() || Modifier.isStatic( conditionType.getModifiers() );
  }
}

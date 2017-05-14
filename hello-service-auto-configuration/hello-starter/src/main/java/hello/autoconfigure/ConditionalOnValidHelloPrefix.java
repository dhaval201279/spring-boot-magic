package hello.autoconfigure;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author Dhaval Shah
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnValidHelloPrefixCondition.class)
public @interface ConditionalOnValidHelloPrefix {
}

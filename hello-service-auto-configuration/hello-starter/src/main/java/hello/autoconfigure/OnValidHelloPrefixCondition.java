package hello.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Dhaval Shah
 */
public class OnValidHelloPrefixCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println("Entering OnValidHelloPrefixCondition : getMatchOutcome");
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(context.getEnvironment(), "hello.");
        ConditionMessage.Builder condition = ConditionMessage.forCondition("ValidHelloPrefix");
        if (resolver.containsProperty("prefix")) {
            String value = resolver.getProperty("prefix");
            if (Character.isUpperCase(value.charAt(0))) {
                System.out.println("Leaving OnValidHelloPrefixCondition : getMatchOutcome - 1");
                return ConditionOutcome.match(condition.available(String.format("valid prefix ('%s')", value)));
            }
            System.out.println("Leaving OnValidHelloPrefixCondition : getMatchOutcome - 2");
            return ConditionOutcome.noMatch(condition.because(String.format("rejected the prefix ‘%s’ as it " +
                            "does not start with an upper-case character", value)));

        }
        System.out.println("Leaving OnValidHelloPrefixCondition : getMatchOutcome - 3");
        return ConditionOutcome.noMatch(condition.didNotFind("property").items("'hello.prefix'"));
    }
}

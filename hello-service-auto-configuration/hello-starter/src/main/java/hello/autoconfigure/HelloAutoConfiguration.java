package hello.autoconfigure;

import hello.ConsoleHelloService;
import hello.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  Dhaval Shah
 */
@Configuration
//@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    private final HelloProperties helloProperties;

    public HelloAutoConfiguration(HelloProperties helloProperties) {
        System.out.println("Entering constructor HelloAutoConfiguration with helloProperties");
        this.helloProperties = helloProperties;
        System.out.println("Leaving constructor HelloAutoConfiguration with helloProperties");
    }

    @Bean
    @ConditionalOnMissingBean
    //@Conditional(OnValidHelloPrefixCondition.class)
    @ConditionalOnValidHelloPrefix
    public HelloService helloService() {
        System.out.println("Entering HelloAutoConfiguration : helloService");
        System.out.println("Instantiating ConsoleHelloService and leaving HelloAutoConfiguration : helloService");
        return new ConsoleHelloService(helloProperties.getPrefix(), helloProperties.getSuffix());

    }
}

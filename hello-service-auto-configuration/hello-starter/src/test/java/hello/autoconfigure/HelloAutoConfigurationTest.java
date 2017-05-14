package hello.autoconfigure;

import hello.ConsoleHelloService;
import hello.HelloService;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class HelloAutoConfigurationTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public OutputCapture output = new OutputCapture();

	private ConfigurableApplicationContext context;

	@After
	public void closeContext() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void defaultServiceIsAutoConfigured() {
		/**
		 * Programmatic way of creating spring's application context by passing Configuration's class
		 * */
		//load(EmptyConfiguration.class, "hello.prefix=Test", "hello.suffix=**");
		load(EmptyConfiguration.class, "hello.prefix=Howdy");
		//load(EmptyConfiguration.class);
		HelloService bean = this.context.getBean(HelloService.class);
		bean.sayHello("World");
		this.output.expect(containsString("Howdy World!"));
	}

	@Test
	public void defaultServiceBacksOff() {
		load(UserConfiguration.class);
		HelloService bean = this.context.getBean(HelloService.class);
		bean.sayHello("Works");
		this.output.expect(containsString("Mine Works**"));
	}

	@Test
	public void defaultServiceIsNotAutoConfiguredIfPrefixIsMissing() {
		load(EmptyConfiguration.class);
		assertThat(this.context.getBeansOfType(HelloService.class)).isEmpty();
	}

	@Test
	public void defaultServiceIsNotAutoConfiguredWithWrongPrefix() {
		/*this.thrown.expect(BeanCreationException.class);
		this.thrown.expectMessage("Invalid prefix 'test'");
		load(EmptyConfiguration.class, "hello.prefix=test");*/
		load(EmptyConfiguration.class,"hello.prefix=invalid");
		assertThat(this.context.getBeansOfType(HelloService.class)).isEmpty();
	}

	private void load(Class<?> config, String... environment) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext();
		ctx.register(config);
		EnvironmentTestUtils.addEnvironment(ctx, environment);
		ctx.refresh();
		this.context = ctx;
	}

	@Configuration
	@ImportAutoConfiguration(HelloAutoConfiguration.class)
	static class EmptyConfiguration {

	}

	@Configuration
	@Import(EmptyConfiguration.class)
	static class UserConfiguration {

		/*@Bean
		public HelloService myHelloService() {
			return new ConsoleHelloService("Mine", "**");
		}*/

		@Bean
		public HelloService helloService() {
			System.out.println("Entering HelloAutoConfigurationTest.UserConfiguration : helloService");
			System.out.println("Instantiating ConsoleHelloService and leaving " +
					"HelloAutoConfigurationTest.UserConfiguration : helloService");
			return new ConsoleHelloService("Mine", "**");
		}
	}

}
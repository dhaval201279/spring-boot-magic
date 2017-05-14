package hello.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties are simple POJOs and hence there is nothing specific about them. By adding
 * below annotation we are asking
 * Asking spring boot to bind environment with certain key to your POJO instance
 * */
@ConfigurationProperties("hello")
public class HelloProperties {

	/**
	 * Prefix of the welcome message. A space is added before the prefix and
	 * the actual message.
	 */
	//private String prefix = "Hello";
	private String prefix;

	/**
	 * Suffix of the welcome message.
	 */
	private String suffix = "!";

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}

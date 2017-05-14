package hello;

public class ConsoleHelloService implements HelloService {

	private final String prefix;

	private final String suffix;

	public ConsoleHelloService(String prefix, String suffix) {
		System.out.println("Entering constructor ConsoleHelloService with prefix :" + prefix + " and suffix : " + suffix);
		this.prefix = (prefix != null ? prefix : "Hello");
		this.suffix = (suffix != null ? suffix : "!");
		/*if (!Character.isUpperCase(this.prefix.charAt(0))) {
			throw new InvalidHelloPrefixException(this.prefix);
		}*/
		System.out.println("Leaving constructor ConsoleHelloService with prefix :" + prefix + " and suffix : " + suffix);
	}

	public ConsoleHelloService() {
		this(null, null);
		System.out.println("Leaving empty constructor ConsoleHelloService");
	}

	@Override
	public void sayHello(String name) {
		System.out.println("Entering ConsoleHelloService : sayHello");
		String msg = String.format("%s %s%s", this.prefix, name, this.suffix);
		System.out.println(msg);
		System.out.println("Leaving ConsoleHelloService : sayHello");
	}

}

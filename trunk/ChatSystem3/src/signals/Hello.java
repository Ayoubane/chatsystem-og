package signals;

/**
 * The Hello Signal
 * @author Ayoub, Omar
 */
public class Hello extends Signal {
	private String username;

        /**
         * Creates a Hello Packet
         * @param username 
         */
	public Hello(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

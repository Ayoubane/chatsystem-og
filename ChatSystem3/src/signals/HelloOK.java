package signals;

/**
 * The HelloOk Signal
 * @author Ayoub, Omar
 */
public class HelloOK extends Signal {
	private String username;

        /**
         * Creates a HelloOk Packet
         * @param username 
         */
	public HelloOK(String username) {
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

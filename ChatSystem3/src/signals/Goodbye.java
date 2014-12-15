package signals;

/**
 * The GoodBye Packet
 * @author Ayoub, Omar
 */
public class Goodbye extends Signal {
	private String username;

        /**
         * Creates a GoodBye packet
         * @param username 
         */
	public Goodbye(String username) {
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

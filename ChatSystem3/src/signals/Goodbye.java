package signals;

import java.io.Serializable;

public class Goodbye extends Signal {
	private String username;

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

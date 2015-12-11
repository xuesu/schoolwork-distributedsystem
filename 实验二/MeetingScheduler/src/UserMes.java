import java.io.Serializable;

public class UserMes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9160963893721607964L;
	String name,password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserMes(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserMes [name=" + name + ", password=" + password + "]";
	}
	
}

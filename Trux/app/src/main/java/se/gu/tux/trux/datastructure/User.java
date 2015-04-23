package se.gu.tux.trux.datastructure;

/**
 * Created by jonas on 3/24/15.
 */
public class User extends Data
{
	/**
	 * To try to log in or to register a new user, set the session id 
	 * to one of the following:
	 */
	public static final int LOGIN_REQUEST = -1;
	public static final int REGISTER_REQUEST = -2;
	private String username;
	private String passwordHash;
	private String firstName;
	private String lastName;

	public boolean passwordMatch(String hash) {
		return hash.equals(passwordHash);
	}

	@Override
	public Object getValue() {
		return username;
	}

	@Override
	public void setValue(Object value) {
		// Nothing for now
	}

	@Override
	public boolean isOnServerSide() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

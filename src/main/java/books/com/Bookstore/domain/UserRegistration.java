package books.com.Bookstore.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistration {

	@NotEmpty
	@Size(min = 6, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 6, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 6, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistration(@NotEmpty @Size(min = 6, max = 30) String username,
			@NotEmpty @Size(min = 6, max = 30) String password, @NotEmpty @Size(min = 4, max = 30) String passwordCheck,
			@NotEmpty String role) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}

	public UserRegistration(@NotEmpty @Size(min = 6, max = 30) String username,
			@NotEmpty @Size(min = 6, max = 30) String password,
			@NotEmpty @Size(min = 6, max = 30) String passwordCheck) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRegistration [username=" + username + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ ", role=" + role + "]";
	}

}

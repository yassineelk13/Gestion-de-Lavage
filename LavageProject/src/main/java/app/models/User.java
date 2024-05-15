package app.models;


public class User {
	protected int id;
	protected String full_name;
	protected String email;
	protected String password;
	
	public User() {
	}
	
	public User(String name, String email, String country) {
		super();
		this.full_name = name;
		this.email = email;
		this.password = country;
	}
	
	public User(int id ,String name, String email, String country) {
		super();
		this.id = id;
		this.full_name = name;
		this.email = email;
		this.password = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}

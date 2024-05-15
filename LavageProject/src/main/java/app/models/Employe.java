package app.models;

public class Employe {

	private int id;
    private String fullName;
    private String username;
    private String Password; // Assuming password is hashed before storing
    private double salary;

    public Employe(int id, String fullName, String username, String Password, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.Password = Password;
        this.salary = salary;
    }
    
    public Employe( String fullName, String username, String Password, double salary) {
        this.fullName = fullName;
        this.username = username;
        this.Password = Password;
        this.salary = salary;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
    
    
	
}

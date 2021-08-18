package entity;

public class User {
	private int userId;
	private String firstname;
	private String lastname;
	private String emailAddress;
	
	public User(int id, String firstname, String lastname, String emailAddress)
	{
		this.userId = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailAddress = emailAddress;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailaddress) {
		this.emailAddress = emailaddress;
	}
}
package br.com.dbconnect;

public class User {

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String firstname;
	private String lastname;
	private int age;

	public User() {
		super();
	}

	public User(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}

	// Gere os métodos get/set

	@Override
	public String toString() {
		return "User{firstName='" + firstname
				+ '\'' + ", lastName='" + lastname + '\'' + ", age=" + age
				+ '}';
	}

}

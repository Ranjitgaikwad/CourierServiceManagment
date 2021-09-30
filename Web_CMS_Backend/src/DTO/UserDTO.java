package com.app.DTO;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.app.pojo.Role;
@CrossOrigin(origins = "http://localhost:3000")
public class UserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
	
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
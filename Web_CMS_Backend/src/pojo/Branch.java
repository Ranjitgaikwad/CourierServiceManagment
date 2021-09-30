package com.app.pojo;

import javax.persistence.*;

@Entity
@Table(name="branch")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20,nullable = false)
	private String name;
	@Column(length = 20,nullable = false)
	private int contact;
	@Column(length = 20,unique = true)
	private String email;
	@Column(length = 100,nullable = false)
	private String address;
	@Column(length = 20,nullable = false)
	private String city;
	@Column(length = 20,nullable = false)
	private int pincode;
	@Column(length = 20,nullable = false)
	private String state;
	@Column(length = 20,nullable = false)
	private String country;
	
	public Branch() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public Branch(int id, String name, int contact, String email, String address, String city, int pincode,
			String state, String country) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", address="
				+ address + ", city=" + city + ", pincode=" + pincode + ", state=" + state + ", country=" + country
				+ "]";
	}

	
	
}
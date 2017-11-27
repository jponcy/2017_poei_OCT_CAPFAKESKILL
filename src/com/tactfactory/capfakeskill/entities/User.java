package com.tactfactory.capfakeskill.entities;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public class User extends BaseEntity {

	private String lastname;
	private String firstname;
	private String email;
	private String password;
	private Integer id_carrer_manager;

	public Integer getId_carrer_manager() {
		return id_carrer_manager;
	}

	public void setId_carrer_manager(Integer id_carrer_manager) {
		this.id_carrer_manager = id_carrer_manager;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getLogin() {
		return this.firstname + "." + this.lastname;
	}

	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname
				+ ", email=" + email + ", password=" + password + ", getId()="
				+ getId() + "]";
	}
}

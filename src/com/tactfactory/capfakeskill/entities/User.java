package com.tactfactory.capfakeskill.entities;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

@Table(name="user")
public class User extends BaseEntity {

	@Column(name="lastname")
	private String lastname;

	@Column(name="firstname")
	private String firstname;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	@OneToMany(targetEntity=CarrerManager.class,mappedBy="collaborators")
	@Column(name="id_carrer_manager")
	private User carrer_manager;

	public User getCarrer_manager() {
		return carrer_manager;
	}

	public void setId_carrer_manager(User carrer_manager) {
		this.carrer_manager = carrer_manager;
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

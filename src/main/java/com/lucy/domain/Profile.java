package com.lucy.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucy.validator.EqualPasswords;

//import com.lucy.validator.ProfileId;

@EqualPasswords
@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Size(min = 2, max = 50, message = "{Size.name.validation}")
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 50, message = "{Size.name.validation}")
	private String lastName;

	@NotEmpty
	@Size(min = 4, max = 50, message = "{message.name.validation}")
	private String userName;

	@NotEmpty
	private String password;

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	// @NotEmpty
	@Transient
	private String confirmpassword;

	@NotEmpty
	@Email
	private String email;

	/*
	 * /* private String userStatus; private int isActive;
	 */

	/*
	 * @NotNull
	 * 
	 * @Past
	 * 
	 * @DateTimeFormat(pattern = "dd/MM/yyyy") private Date birthdate;
	 */

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Role role;

	public Profile() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public Date getBirthdate() { return birthdate; }
	 * 
	 * public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }
	 */
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}

package com.lucy.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String role;
	
	@Valid
	@ManyToMany
	private List<Profile> profiles;
	
}

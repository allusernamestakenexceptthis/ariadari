package com.gomilkyway.profile.adari.user;

import java.util.HashSet;
import java.util.Set;

import com.gomilkyway.profile.adari.utils.PasswordUtil;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity
 * 
 */
@Entity
@Setter @Getter @EqualsAndHashCode
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String username;

	private String name;
	private String email;

	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
	@Setter(value = AccessLevel.NONE)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles = new HashSet<>();

	/**
	 * Default constructor, sets default role to READER
	 * 
	 */
	public User(){
		this.roles.add(UserRole.READER);
	}

	/**
	 * Id getter and setter
	 * 
	 */
	/*
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	} */

	/**
	 * Username getter and setter
	 * 
	 */
	/*
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	} */

	/**
	 * Name getter and setter
	 * 
	 */
	/*
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	} */

	/**
	 * Email getter and setter
	 * 
	 */
	/*
	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	} */

	/**
	 * Password getter and setter
	 * 
	 */
	/*
	public String getPassword(){
		return this.password;
	}
 	*/
	public void setPassword(String password){
		this.password = PasswordUtil.encrypt(password);
	}
	
	/**
	 * Projection Interface to return limited user info
	 * 
	 */
	public interface LimitedUserInfo {
		String getName();
		String getEmail();
		UserRole getRoles();
	}

}

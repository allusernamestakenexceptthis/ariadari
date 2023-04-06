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
import lombok.ToString;


/**
 * User entity
 * 
 */
@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false, columnDefinition = "VARCHAR(30) NOT NULL")
	private String username;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
	private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
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

package com.gomilkyway.profile.adari.user;

import java.util.Set;

import com.gomilkyway.profile.adari.annotations.MatchPassword;
import com.gomilkyway.profile.adari.annotations.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MatchPassword(passwordField = "password", confirmField = "confirmPassword", message = "Passwords do not match")
public class UserDTO {

	private Long id;

    @NotBlank
    @Size(min=5, max=30, message = "Username must be between 5 and 30 characters")
	private String username;

    @Size(min=0, max=50, message = "Name must be between 2 and 50 characters")
	private String name;

    @NotBlank
    @Size(max=255, message = "Email must be less than 255 characters")
    @Email(message = "Email must be valid")
	private String email;

	@ValidPassword
	private String password;

    private String confirmPassword;

    private Set<UserRole> roles;
}

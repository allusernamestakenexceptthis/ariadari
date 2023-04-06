package com.gomilkyway.profile.adari.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.gomilkyway.profile.adari.user.User.LimitedUserInfo;

/**
 * User repository
 * 
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	Iterable<LimitedUserInfo> findAllBy(Class<LimitedUserInfo> type);

	Optional<User> findByUsername(String username);
	
}

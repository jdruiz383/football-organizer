package com.footballorg.interfaces;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.footballorg.models.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	
	//jPA functions that allow for the authentication of the user by username and Password along with checking that the user is not registering a duplicate account.
	Optional<User> findByLoginAndPassword(String login, String password);
	
	Optional<User> findFirstByLogin(String login);
}

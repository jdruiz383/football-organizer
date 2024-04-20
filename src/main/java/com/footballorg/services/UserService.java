package com.footballorg.services;

import org.springframework.stereotype.Service;

import com.footballorg.interfaces.UserRepository;
import com.footballorg.models.User;

@Service
public class UserService 
{

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	//method to register the user to the database utilizing the userRepository to check for duplicate logins. uses the JPA repository to upload the data to the datbase via the @entity and @table annotations in the user model.
	public User registerUser(String login, String password, String email, String firstName, String lastName)
	{
		if(login == null && password == null)
		{
			return null;
		}
		else
		{
			if(userRepository.findFirstByLogin(login).isPresent())
					{
						System.out.println("Duplicate login");
						return null;
					}
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			return userRepository.save(user);
		}
	}
	
	public User authenticate(String login, String password)
	{
		return userRepository.findByLoginAndPassword(login, password).orElse(null);
	}
}

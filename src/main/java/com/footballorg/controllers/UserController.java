package com.footballorg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.footballorg.models.User;
import com.footballorg.services.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	//Mapping for post and get API calls that will return the proper pages based on the action taken. These feed into the html pages so that each page of the UI functions correctly. 
	@GetMapping("/index")
	public String getHomePage()
	{
		return "index";
	}
	
	//returns the register page.
	@GetMapping("/register")
	public String getRegisterPage(Model model)
	{
		model.addAttribute("registerRequest", new User());
		return "register_page";
	}
	
	//return the login page 
	@GetMapping("/login")
	public String getLoginPage(Model model)
	{	
		model.addAttribute("loginRequest", new User());
		return "login_page";
	}
	
	//takes inputs from the register page and feeds them into the user service to get them updated to the database.
	@PostMapping("/register")
	public String register(@ModelAttribute User user)
	{
		System.out.println("register request: " + user);
		User registeredUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName());
		return registeredUser == null ? "error_page" : "redirect:/login";
	}
	
	//checks login and password inputs to authenticate them against the database credentials.
	@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model)
	{
		System.out.println("login request: " + user);
		User authenticated = userService.authenticate(user.getLogin(), user.getPassword());
		if(authenticated != null)
		{	
			model.addAttribute("userLogin", authenticated.getLogin());
			return "personal_page";
		}
		else 
		{
			return "error_page";
		}
	}
}

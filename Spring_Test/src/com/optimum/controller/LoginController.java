package com.optimum.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.optimum.dao.UserDao;
import com.optimum.model.User;

@Controller
public class LoginController {
	
	private UserDao refCheck;
	
	public LoginController() {
		refCheck = new UserDao();
	}
	
	@RequestMapping("checkUser")
	public ModelAndView checkUser() {
		return new ModelAndView("LoginCheck","checkUser",new User());
	}
	
	@RequestMapping("saveUser")
	public String saveUser(@ModelAttribute("checkUser") User refUser, ModelMap model) {
		
		try {
			if(refCheck.loginAuthenticator(refUser) == false) {
				return ("noEntry");
			}
			else if(refCheck.loginAuthenticator(refUser) == true && refUser.getUserName().equals("admin")) {
				return("adminPage");
			}else {
				model.addAttribute("userID", refUser.getUserName());
				return("successfulLogin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}

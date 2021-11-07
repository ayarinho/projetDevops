package tn.esprit.spring.entityTest;



import org.junit.jupiter.api.Test;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.TimesheetApplication;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.UserServiceImpl;


@SpringBootTest(classes = TimesheetApplication.class)
class UserTest {

	 
	 @Autowired
		UserServiceImpl userService;
      
	  Role role;
		
		@Test
		void contextLoads() {
			
	  
		}
		

		@Test
		void deleteUserById() {
		
			//Boolean res= userService.deleteUserById(66);
			
			//org.junit.jupiter.api.Assertions.assertFalse(res==false);
			//org.junit.jupiter.api.Assertions.assertTrue(res!=false);
			
		}
		

		@Test
		void authenticate() {
		
			User user= userService.authenticate("youssef@gmail.fr", "ayarinho");
			
			//org.junit.jupiter.api.Assertions.assertFalse(user==null);
			//org.junit.jupiter.api.Assertions.assertTrue(user!=null);
			
		}
		
		
		@Test
		void addUser() {
			
			User us = new User(1, "ayari", "youssef", "youssef@gmail.com","26578963", "ayarinho", role.ADMINISTRATEUR);
		
			int userId = userService.addUser(us);
			
			org.junit.jupiter.api.Assertions.assertFalse(userId==0);
			org.junit.jupiter.api.Assertions.assertTrue(userId!=0);
		}
	 
		
		@Test
		void updateUserWithEmail() {
			
		
		Boolean res  =	 userService.updateUserWithEmail("sami@gmail.com", 9);
			
		org.junit.jupiter.api.Assertions.assertFalse(res==false);
		org.junit.jupiter.api.Assertions.assertTrue(res!=false);
		}
		
		
		
		@Test
		void getUserPrenomById() {
			
		
		String prenom = userService.getUserPrenomById(22);
			
		org.junit.jupiter.api.Assertions.assertFalse(prenom=="");
		org.junit.jupiter.api.Assertions.assertTrue(prenom!="");
		}
		
		
		@Test
		void getAllUsers() {
			
		
		List<User> users = userService.getAllUsers();
			
		org.junit.jupiter.api.Assertions.assertFalse(users==null);
		org.junit.jupiter.api.Assertions.assertTrue(users!=null);
		}
	 
}

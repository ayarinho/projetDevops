package tn.esprit.spring.entityTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.UserServiceImpl;

@SpringBootTest
class UserTest {

	 
	 @Autowired
		UserServiceImpl userService;
      
	  Role role;
		
		@Test
		void contextLoads() {
			
	  
		}
		

		@Test
		void deleteUserById() {
		
			Boolean res= userService.deleteUserById(0);
			
			assertFalse("error",  res==false);
			assertTrue("succefully", res != false);
		}
		

		@Test
		void authenticate() {
		
			User user= userService.authenticate("youssef@gmail.com", "ayarinho");
			
			assertFalse("error",  user==null);
			assertTrue("succefully", user != null);
		}
		
		
		@Test
		void addUser() {
			
			User us = new User(1, "ayari", "youssef", "youssef@gmail.com","26578963", "ayarinho", role.ADMINISTRATEUR);
		
			int userId = userService.addUser(us);
			
			assertFalse("error",  userId==0);
			assertTrue("succefully", userId != 0);
		}
	 
		
		@Test
		void updateUserWithEmail() {
			
		
		Boolean res  =	 userService.updateUserWithEmail("sami@gmail.com", 2);
			
			assertFalse("error",  res==false);
			assertTrue("succefully", res != false);
		}
		
		
		
		@Test
		void getUserPrenomById() {
			
		
		String prenom = userService.getUserPrenomById(2);
			
			assertFalse("error",  prenom== " ");
			assertTrue("succefully", prenom != " ");
		}
		
		
		@Test
		void getAllUsers() {
			
		
		List<User> users = userService.getAllUsers();
			
			assertFalse("error",  users== null);
			assertTrue("succefully", users != null);
		}
	 
}

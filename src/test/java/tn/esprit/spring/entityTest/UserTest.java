package tn.esprit.spring.entityTest;

<<<<<<< HEAD


import org.junit.jupiter.api.Test;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.TimesheetApplication;
=======
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.UserServiceImpl;

<<<<<<< HEAD

@SpringBootTest(classes = TimesheetApplication.class)
=======
@SpringBootTest
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
class UserTest {

	 
	 @Autowired
		UserServiceImpl userService;
      
	  Role role;
		
		@Test
		void contextLoads() {
			
	  
		}
		

		@Test
		void deleteUserById() {
		
<<<<<<< HEAD
			//Boolean res= userService.deleteUserById(66);
			
			//org.junit.jupiter.api.Assertions.assertFalse(res==false);
			//org.junit.jupiter.api.Assertions.assertTrue(res!=false);
			
=======
			Boolean res= userService.deleteUserById(0);
			
			assertFalse("error",  res==false);
			assertTrue("succefully", res != false);
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
		

		@Test
		void authenticate() {
		
<<<<<<< HEAD
			User user= userService.authenticate("youssef@gmail.fr", "ayarinho");
			
			//org.junit.jupiter.api.Assertions.assertFalse(user==null);
			//org.junit.jupiter.api.Assertions.assertTrue(user!=null);
			
=======
			User user= userService.authenticate("youssef@gmail.com", "ayarinho");
			
			assertFalse("error",  user==null);
			assertTrue("succefully", user != null);
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
		
		
		@Test
		void addUser() {
			
			User us = new User(1, "ayari", "youssef", "youssef@gmail.com","26578963", "ayarinho", role.ADMINISTRATEUR);
		
			int userId = userService.addUser(us);
			
<<<<<<< HEAD
			org.junit.jupiter.api.Assertions.assertFalse(userId==0);
			org.junit.jupiter.api.Assertions.assertTrue(userId!=0);
=======
			assertFalse("error",  userId==0);
			assertTrue("succefully", userId != 0);
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
	 
		
		@Test
		void updateUserWithEmail() {
			
		
<<<<<<< HEAD
		Boolean res  =	 userService.updateUserWithEmail("sami@gmail.com", 9);
			
		org.junit.jupiter.api.Assertions.assertFalse(res==false);
		org.junit.jupiter.api.Assertions.assertTrue(res!=false);
=======
		Boolean res  =	 userService.updateUserWithEmail("sami@gmail.com", 2);
			
			assertFalse("error",  res==false);
			assertTrue("succefully", res != false);
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
		
		
		
		@Test
		void getUserPrenomById() {
			
		
<<<<<<< HEAD
		String prenom = userService.getUserPrenomById(22);
			
		org.junit.jupiter.api.Assertions.assertFalse(prenom=="");
		org.junit.jupiter.api.Assertions.assertTrue(prenom!="");
=======
		String prenom = userService.getUserPrenomById(2);
			
			assertFalse("error",  prenom== " ");
			assertTrue("succefully", prenom != " ");
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
		
		
		@Test
		void getAllUsers() {
			
		
		List<User> users = userService.getAllUsers();
			
<<<<<<< HEAD
		org.junit.jupiter.api.Assertions.assertFalse(users==null);
		org.junit.jupiter.api.Assertions.assertTrue(users!=null);
=======
			assertFalse("error",  users== null);
			assertTrue("succefully", users != null);
>>>>>>> 829615410efb111daf32839bd5894ee5868a1e45
		}
	 
}

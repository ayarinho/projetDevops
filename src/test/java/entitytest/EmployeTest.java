package entitytest;




import org.junit.jupiter.api.Test;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.TimesheetApplication;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.EmployeServiceImpl;


@SpringBootTest(classes = TimesheetApplication.class)

public class EmployeTest {

	 @Autowired
		EmployeServiceImpl userService;
   
	  Role role;
		
	  @Test
		void contextLoads() {
			
		//this methos should be empty
		}
		

		@Test
		void deleteUserById() {
		
			//Boolean res= userService.deleteUserById(66);
			
			//org.junit.jupiter.api.Assertions.assertFalse(res==false);
			//org.junit.jupiter.api.Assertions.assertTrue(res!=false);
			
		}
		

		@Test
		void authenticate() {
		
			Employe employe= userService.authenticate("youssef@gmail.fr", "trtr");
			
			//org.junit.jupiter.api.Assertions.assertFalse(employe==null);
			//org.junit.jupiter.api.Assertions.assertTrue(employe!=null);
		
			
		}
		
		
		@Test
		void addEmploye() {
			
			Employe em = new  Employe("amir", "rkik", "amir.rkik@esprit.tn", true, Role.CHEF_DEPARTEMENT);
		
			int userId = userService.ajouterEmploye(em);
			
			org.junit.jupiter.api.Assertions.assertFalse(userId==0);
			org.junit.jupiter.api.Assertions.assertTrue(userId!=0);
		}
	 
		
		@Test
		void updateEmployeWithEmail() {
			
		
		Boolean res  =	 userService.updateEmployeWithEmail("sami@gmail.com", 9);
			
		org.junit.jupiter.api.Assertions.assertFalse(res==false);
		org.junit.jupiter.api.Assertions.assertTrue(res!=false);
		}
		
		
		
		@Test
		void getEmployePrenomById() {
			
		
		String prenom = userService.getEmployePrenomById(22);
			
		org.junit.jupiter.api.Assertions.assertFalse(prenom=="");
		org.junit.jupiter.api.Assertions.assertTrue(prenom!="");
		}
		
		
		@Test
		void getAllemployes() {
			
		
		List<Employe> users = userService.getAllEmploye();
			
		org.junit.jupiter.api.Assertions.assertFalse(users==null);
		org.junit.jupiter.api.Assertions.assertTrue(users!=null);
		}
	 
}

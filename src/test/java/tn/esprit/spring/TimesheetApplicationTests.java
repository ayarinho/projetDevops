package tn.esprit.spring;



import static org.junit.Assert.assertTrue;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EmployeServiceImpl;

@SpringBootTest
class TimesheetApplicationTests {
 
	Employe e;
	Role role;
	
	
	@Autowired
	EmployeServiceImpl employerservie;
 	

	
	@Test
	void contextLoads() {
		
  
	}
	

	@Test
	void deleteEmployerById() {
	
		 employerservie.deleteEmployeById(6);
		
		//assertTrue("error",  null);
		//assertTrue("succefully", prenom != null);
	}

	
	@Test
	void getEmployerByPrenom() {
	
		String prenom = employerservie.getEmployePrenomById(2);
		
		assertTrue("error", prenom == null);
		assertTrue("succefully", prenom != null);
	}
	
	
	@Test
	void authentification() {
		
		e= employerservie.authenticate("youssef@gmail.fr", "ayarossa");
			
			assertTrue("error", e == null);
			assertTrue("succefully", e != null);
			
	      }
	
	@Test
	void addEmploye() {
	
		int idEmp ;
		
	idEmp= employerservie.addOrUpdateEmploye(new Employe("youssef", "ayari", "youssef@gmail.fr", true, role.ADMINISTRATEUR));
		
		assertTrue("error add", idEmp == 0);
		assertTrue("succefully add", idEmp != 0);
		
		
		
	}
	
	/*@Test
	void updateEmploye() {
	
	
		
	 employerservie.mettreAjourEmailByEmployeId("amir@gmail.fr", 2);
		
		//assertTrue("error add", null);
		//assertTrue("succefully add", null);
		
	}

	
	@Test
	void affecterEmployerDepartement() {
	
		employerservie.desaffecterEmployeDuDepartement(0,0);
		
		
	}
	
}*/
	
	
	
}

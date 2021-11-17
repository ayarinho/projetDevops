package tn.esprit.spring;






import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IContratService;


@SpringBootTest
public class ContratServiceImplTest {
	
	@Autowired
	EmployeRepository employerep ;
	@Autowired
	IContratService contratService ;
	
	Date currentDate = new Date(System.currentTimeMillis());
	
	Employe employee = new Employe("test", "junit", "test@junit.com", true, Role.TECHNICIEN);
	
	
	int idEmp = 0 ; 
	int idCont = 0 ;
	
	
	@Test	
	public void testAjouterContrat() {
		Contrat contrat = new Contrat(currentDate, "CDI", 1000);
		 contratService.ajouterContrat(contrat);
		
		
		
	}
	@Test 
	public void testgetAllContrats() {
		contratService.getAllContrats();
	}
	@Test
	public  void testAffecterContratAEmploye() {
		
		List <Contrat> contracts = contratService.getAllContrats();
		for (Contrat cont : contracts) {
			idCont = cont.getReference(); 
		}
			
		List <Employe> employees =(List <Employe>) employerep.findAll();
		for (Employe emp : employees) {
			idEmp = emp.getId(); 
		}
		
		contratService.affecterContratAEmploye(idCont, idEmp);
		

	}
	
	@Test
	public	void testDeleteContratById() {
		
		contratService.deleteContratById(idCont);

	}
	

	@Test
	public void testdeleteAllContratJPQL() {
		
		contratService.deleteAllContratJPQL();

	}
}
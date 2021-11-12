package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;

import tn.esprit.spring.entities.Role;

import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RestController
public class RestControlEmploye {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	public class Empdto{
		 int id;
		 String prenom;
		 String nom;
		 String email;
		 boolean isActif;
		 Role role;

	}

	@PostMapping("/ajouterEmployer")
	@ResponseBody
	public Employe ajouterEmploye(@RequestBody Empdto employe)
	{
		Employe emp = new Employe();

		iemployeservice.ajouterEmploye(emp);
		return emp;
	}

	// Modifier email : http://localhost:8081/SpringMVC/servlet/modifyEmail/1/newemail
	@PutMapping(value = "/modifyEmail/{id}/{newemail}")
	@ResponseBody
	public void mettreAjourEmailByEmployeId(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);

	}
	// http://localhost:8081/SpringMVC/servlet/affecterEmployeADepartement/1/1
	@PutMapping(value = "/affecterEmployeADepartement/{idemp}/{iddept}")
	public void affecterEmployeADepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId) {
		iemployeservice.affecterEmployeADepartement(employeId, depId);

	}

	// http://localhost:8081/SpringMVC/servlet/desaffecterEmployeDuDepartement/1/1
	@PutMapping(value = "/desaffecterEmployeDuDepartement/{idemp}/{iddept}")
	public void desaffecterEmployeDuDepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId)
	{
		iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
	}
	public class Contratdto{
		 int reference;
		 Date dateDebut;
		 String typeContrat;
		 float salaire;
		public int getReference() {
			return reference;
		}

	}
	@PostMapping("/ajouterContrat")
	@ResponseBody
	public int ajouterContrat(@RequestBody Contratdto contrat) {
		Contrat con=new Contrat();

		iemployeservice.ajouterContrat(con);
		return contrat.getReference();
	}





   // URL : http://localhost:8081/SpringMVC/servlet/getEmployePrenomById/2
   @GetMapping(value = "getEmployePrenomById/{idemp}")
   @ResponseBody
   public Employe getEmployePrenomById(@PathVariable("idemp")int employeId) {
		return iemployeservice.getEmployePrenomById(employeId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/deleteEmployeById/1
    @DeleteMapping("/deleteEmployeById/{idemp}")
	@ResponseBody
	public void deleteEmployeById(@PathVariable("idemp")int employeId) {
		iemployeservice.deleteEmployeById(employeId);

	}




    // URL : http://localhost:8081/SpringMVC/servlet/getNombreEmployeJPQL
    @GetMapping(value = "getNombreEmployeJPQL")
    @ResponseBody
	public int getNombreEmployeJPQL() {

		return iemployeservice.getNombreEmployeJPQL();
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeNamesJPQL
    @GetMapping(value = "getAllEmployeNamesJPQL")
    @ResponseBody
	public List<String> getAllEmployeNamesJPQL() {

		return iemployeservice.getAllEmployeNamesJPQL();
	}


 // Modifier email : http://localhost:8081/SpringMVC/servlet/mettreAjourEmailByEmployeIdJPQL/2/newemail
 	@PutMapping(value = "/mettreAjourEmailByEmployeIdJPQL/{id}/{newemail}")
 	@ResponseBody
	public void mettreAjourEmailByEmployeIdJPQL(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {
	iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

    // URL : http://localhost:8081/SpringMVC/servlet/deleteAllContratJPQL
    @DeleteMapping("/deleteAllContratJPQL")
	@ResponseBody
	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();

	}

    // URL : http://localhost:8081/SpringMVC/servlet/getSalaireByEmployeIdJPQL/2
    @GetMapping(value = "getSalaireByEmployeIdJPQL/{idemp}")
    @ResponseBody
	public float getSalaireByEmployeIdJPQL(@PathVariable("idemp")int employeId) {
		return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getSalaireMoyenByDepartementId/2
    @GetMapping(value = "getSalaireMoyenByDepartementId/{iddept}")
    @ResponseBody
	public Double getSalaireMoyenByDepartementId(@PathVariable("iddept")int departementId) {
		return iemployeservice.getSalaireMoyenByDepartementId(departementId);
	}




	 // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployes
	@GetMapping(value = "/getAllEmployes")
    @ResponseBody
	public List<Employe> getAllEmployes() {

		return iemployeservice.getAllEmployes();
	}



}
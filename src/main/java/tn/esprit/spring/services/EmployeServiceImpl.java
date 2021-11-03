package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	Logger logger = LoggerFactory.getLogger(EmployeServiceImpl.class); 

	

	@Override
	public Employe authenticate(String login, String password) {
		
		
		logger.info("Signing In");
		Employe employe = employeRepository.getEmployeByEmailAndPassword(login, password);
		if (employe != null) {
			logger.info("user Logged In seccufully  :"+employe);
		return employe ; 
		}else {
			if (login == "" || password == "") {
				logger.error("Credentials must not be Empty ! ");
			}else {
				logger.error("User doesn't Exist or Wrong Credentials !");
			}
			return null ; 
		}

	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		logger.info("Adding or Updating Employee");
		employeRepository.save(employe);
		logger.info("Employee Added or Updated Succefully !");
		if (employe.getPassword() == "") {
			logger.warn("User Created without Password !");
		}
		return employe.getId();
		
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		logger.info("Updating Employee Email");
		Employe employe = employeRepository.findById(employeId).get();
		if (email != "") {
		employe.setEmail(email);
		employeRepository.save(employe);
		logger.info("Email Updated Succefully !");
		}else {
			logger.error("The new Email Must not Be Empty !");
		}

	}



	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		
		if (Integer.toString(employeId) == "") {
			logger.error("No user to Add !");
		}
		else if (Integer.toString(depId) == "") {
			logger.error("No Departement To Add User to !");
		}else {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		
		logger.info("Adding Employee to Departement");
		
		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);
		}

		// à ajouter? 
		deptRepoistory.save(depManagedEntity); 
		logger.info("Employee : "  + employeManagedEntity.getPrenom() + " Added To Departement : " + depManagedEntity.getName() + " Succefully !"  );

		}
	}

	
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		
		if (Integer.toString(employeId) == "") {
			logger.error("No user to Remove !");
		}
		else if (Integer.toString(depId) == "") {
			logger.error("No Departement To Remove User From !");
		}else {
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		
		if (employeNb != 0) {
			logger.info("Removing Employee from Departement : " + dep.getName() );
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				logger.info("Employee Removed Succefully !");
				break;//a revoir
			}
		}
		}else {
			logger.error("Departement Already Empty !");
		}
		}
	} 

	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);

	}

	public String getEmployePrenomById(int employeId) {
		
       Employe employeManagedEntity =null;

		if (employeId == 0) {
			logger.error("No employer exist !");
		
	
		}else {
		
		 employeManagedEntity = employeRepository.findById(employeId).get();
		logger.info(" Employer exist  :" +employeManagedEntity.getPrenom());

		}
		return employeManagedEntity.getPrenom();

	}
	 
	public void deleteEmployeById(int employeId)
	{
		
		if (employeId == 0) {
			logger.error("No employer exist for deleting !");
		
	
		}else {
		
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
		logger.info("employer deleted succefully");

		}
	}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}

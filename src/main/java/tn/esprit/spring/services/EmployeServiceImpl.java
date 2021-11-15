package tn.esprit.spring.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;


@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;

	Logger logger = LoggerFactory.getLogger(EmployeServiceImpl.class); 

	@Override
	public Employe authenticate(String login, String nom) {
		
		
		logger.info("Signing In");
		Employe user = employeRepository.getEmployeByEmailAndPassword(login, nom);
		if (user != null) {
			logger.info("user Logged In seccufully  :");
		return user ; 
		}else {
			if (login.equals("") || nom.equals("")) {
				logger.error("Credentials must not be Empty ! ");
			}else {
				logger.error("User doesn't Exist or Wrong Credentials !");
			}
			return null ; 
		}

	}

	@Override
	public int ajouterEmploye(Employe employe ) {
		
		logger.info("Adding or Updating user");
		employeRepository.save(employe);
		logger.info("User Added  Succefully !");
		if (employe.getPassword()=="") {
			logger.warn("User Created without Password !");
		}
		return employe.getId();
		
	}

	
	public Boolean updateEmployeWithEmail( String email, int userId) {
		logger.info("Updating User Email");
		
		Optional<Employe> employe = employeRepository.findById(userId);

            if(employe.isPresent()) {
            	
            Employe employe1=	employe.get();
            
            if (email != "") {
        		employe1.setEmail(email);
        		employeRepository.save(employe1);
        		logger.info("Email Updated Succefully !");
        		
        		return true;
        		}else {
        			logger.error("The new Email Must not Be Empty !");
        			
        			return false;
        		}
            }
          
		return true;
		

	}
	
	
	public String getEmployePrenomById(int employeid) {
	
		
        
			Optional<Employe> employe = employeRepository.findById(employeid);


			if (!employe.isPresent()) {
				logger.error("No User exist !");
			
		       return "No User exist";
			}else {
			
				Employe employe1 = employe.get();
			logger.info(" User exist  : ");
			
			return employe1.getPrenom();

			}
			
		}
		 
		public Boolean deleteEmployeById(int userId)
		{
			
			Optional<Employe> employe = employeRepository.findById(userId);

			
			if (!employe.isPresent()) {
				
				logger.error("No User exist for deleting !");
			 return false;
		
			}else {
			
			Employe employe1=employe.get();

			employeRepository.delete(employe1);
			logger.info("User deleted succefully");
			
           return true;
			}
		}

		public List<Employe> getAllEmploye() {
			
			List<Employe> employes=	(List<Employe>) employeRepository.findAll();
			
			
			if(employes != null) {
				
			logger.info("Users are exists ");
			return (List<Employe>) employeRepository.findAll();

			}else {
				
				logger.info("Users does not  exists !");

				
				return employes;
			}
			
		}


	


		@Override
		public void mettreAjourEmailByEmployeId(String email, int employeId) {logger.info("Updating Employee Email");
		Employe emp =new Employe();
		Optional<Employe> employe = employeRepository.findById(employeId);
		if (employe.isPresent()) {
			
			emp=employe.get();
		}
		if (!email.equals("")) {
		emp.setEmail(email);
		employeRepository.save(emp);
		logger.info("Email Updated Succefully !");
		}else {
			logger.error("The new Email Must not Be Empty !");
		}
			
			
		}


		@Override
		public void affecterEmployeADepartement(int employeId, int depId) {
			Departement dep = new Departement();
			Employe emp  = new Employe();
			if (Integer.toString(employeId).equals("")) {
				logger.error("No user to Add !");
			}
			else if (Integer.toString(depId).equals("")) {
				logger.error("No Departement To Add User to !");
			}else {
			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			if (depManagedEntity.isPresent()) {
				 dep = depManagedEntity.get();
				
			}
			Optional <Employe> employeManagedEntity = employeRepository.findById(employeId);
			if (employeManagedEntity.isPresent()) {
				 emp =employeManagedEntity.get();
				
			}
			
			logger.info("Adding Employee to Departement");
			
			if(dep.getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(emp);
				dep.setEmployes(employes);
			}else{

				dep.getEmployes().add(emp);
			}

			 
			deptRepoistory.save(dep); 
			logger.info("Employee : {} Added To Departement  : {} Succefully", emp.getPrenom() , dep.getName()  );

			}
			
			
		}


		@Override
		public void desaffecterEmployeDuDepartement(int employeId, int depId) {
			Departement d =new Departement();
			if (Integer.toString(employeId).equals("")) {
				logger.error("No user to Remove !");
			}
			else if (Integer.toString(depId).equals("")) {
				logger.error("No Departement To Remove User From !");
			}else {
			Optional<Departement>  dep = deptRepoistory.findById(depId);
			if (dep.isPresent()) {
				d=dep.get();
				
			}

			int employeNb = d.getEmployes().size();
			
			if (employeNb != 0) {
				logger.info("Removing Employee from Departement : {}" , d.getName() );
			for(int index = 0; index < employeNb; index++){
				if(d.getEmployes().get(index).getId() == employeId){
					d.getEmployes().remove(index);
					logger.info("Employee Removed Succefully !");
					break;//a revoir
				}
			}
			}else {
				logger.error("Departement Already Empty !");
			}
			}
			
		}


		@Override
		public int ajouterContrat(Contrat contrat) {
			
			return 0;
		}


	


		


		@Override
		public int getNombreEmployeJPQL() {
			return employeRepository.countemp();
		}


		@Override
		public List<String> getAllEmployeNamesJPQL() {
		
			return employeRepository.employeNames();
		}


		@Override
		public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
			
			return employeRepository.getAllEmployeByEntreprisec(entreprise);
		}


		@Override
		public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
			employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
			
		}


		@Override
		public void deleteAllContratJPQL() {
			employeRepository.deleteAllContratJPQL();
			
		}


		@Override
		public float getSalaireByEmployeIdJPQL(int employeId) {
			return employeRepository.getSalaireByEmployeIdJPQL(employeId);
			}


		@Override
		public Double getSalaireMoyenByDepartementId(int departementId) {
			return employeRepository.getSalaireMoyenByDepartementId(departementId);
		}


		@Override
		public List<Employe> getAllEmployes() {
			return (List<Employe>) employeRepository.findAll();
		}
	}
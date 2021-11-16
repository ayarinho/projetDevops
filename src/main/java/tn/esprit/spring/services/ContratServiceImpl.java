package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContratServiceImpl implements IContratService {
	Logger logger = LoggerFactory.getLogger(ContratServiceImpl.class);
	

	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	
	public List<Contrat> getAllContrats() {
		List<Contrat> contrat = new ArrayList<>();
		
		try {
			logger.info("getting Contrats");
			logger.debug("Je vais afficher les contrats.");
			contrat = (List<Contrat>) contratRepository.findAll();
			logger.debug("Je viens de finir l'opération X.");
			logger.info("Out getAllContrarts() without errors.");			
		
		}
		catch (Exception e) { 
				logger.error(e.toString());
				}
		
		return contrat ;

	}
	
	public int ajouterContrat(Contrat contrat) {
		logger.info("Adding Contract");
		if (contrat != null) {
		try {
			logger.info("add Contrat");
			logger.debug("Je vais ajouter un contrat.");
			contratRepository.save(contrat);
		    logger.info("Contract Added Succefully");
			return contrat.getReference();

		}catch(Exception e) {
			logger.error(e.toString());
		}
		}else {
			logger.warn("Contract is Empty ! ");
		}
		return 0;
	}
	
public void affecterContratAEmploye(int contratId, int employeId) {
		
		if (Integer.toString(employeId).equals("")) {
			logger.warn("No Emploee Assigned !");
		}
		else if (Integer.toString(contratId).equals("")) {
			logger.warn("No Contract to Assign to the Employee !");
		}else {
			
			try {
		
		Optional<Contrat> contratManagedEntity = contratRepository.findById(contratId);
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		
		logger.info("Affecting Contract  " );
		if(contratManagedEntity.isPresent() && employeManagedEntity.isPresent()) {
		Contrat contrat = contratManagedEntity.get();
		Employe employe = employeManagedEntity.get();
		contrat.setEmploye(employe);
		contratRepository.save(contrat);
		}
		
		logger.info("Contract Binded Succefully ! ");
			}catch(Exception e) {
				logger.error(e.toString());
			}
		}

	}

public void deleteContratById(int contratId) {
	if (!(Integer.toString(contratId).equals(""))) {
	logger.info("Deleting Contract");
	try {
	Optional<Contrat> contratManagedEntity = contratRepository.findById(contratId);
	if(contratManagedEntity.isPresent()) {
	Contrat contrat = contratManagedEntity.get();
	contratRepository.delete(contrat);
	}
	logger.info("Contract Deleted Succefully ! ");
	}catch(Exception e) {
		logger.error(e.toString());
	}
	}else {
		logger.warn("No Contract To Delete ! ");
	}

}

public void deleteAllContratJPQL() {
	try {
		logger.info("delete Contrat");
		logger.debug("Je vais supprimer un contrat.");
		employeRepository.deleteAllContratJPQL();
		logger.debug("delete finish");
		logger.info("Contract deleted Succefully ! ");			
	}catch(Exception e) {
		logger.error(e.toString());
	}
}

	

}


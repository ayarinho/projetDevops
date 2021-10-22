package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	public static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	//kamel
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			logger.info("In ajouterEntreprise():");
			logger.debug("debut d'ajout de l'entreprise: " + entreprise.getName());
			entrepriseRepoistory.save(entreprise);
			logger.info("out of ajouterEntreprise()");
			logger.debug("l'entreprise: " + entreprise.getName() + " de l'id: " + entreprise.getId() + " ajoutée avec succé");

		}catch(Exception e){
			logger.error("Erreur: "+e);
		}
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		logger.info("In ajouterDepartement():");
		logger.debug("debut d'ajout de l'entreprise: " + dep.getName());
		deptRepoistory.save(dep);
		logger.info("out of ajouterDepartement()");
		logger.debug("le departement : " + dep.getName() + " de l'id: " + dep.getId() + " ajoutée avec succé");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				logger.info("In affecterDepartementAEntreprise():");
				logger.debug("debut de l'affectation:");
				Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
				logger.info("out of affecterDepartementAEntreprise()");
				Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
				if(entrepriseManagedEntity.isPresent() && depManagedEntity.isPresent() ) {
					depManagedEntity.get().setEntreprise(entrepriseManagedEntity.get());
					deptRepoistory.save(depManagedEntity.get());
					logger.debug("le departement : " + depManagedEntity.get().getName() + " de l'id: " + depId + " affectée avec succée");
				}
				
				
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		logger.info("In getAllDepartementsNamesByEntreprise():");
		logger.debug("debut de recuperation des departements:");
		
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if(entrepriseManagedEntity.isPresent()) {
		logger.info("out of getAllDepartementsNamesByEntreprise()");
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.get().getDepartements()){
			depNames.add(dep.getName());
		}
		logger.debug("les departements sont recuperer avec succées : ");
		return depNames;
		}
		return null;
		
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		logger.info("In deleteEntrepriseById():");
		logger.debug("debut de delete entreprise:");
		Optional<Entreprise> entreprise=entrepriseRepoistory.findById(entrepriseId);
		if(entreprise.isPresent()) {
			entrepriseRepoistory.delete(entreprise.get());	
		}
		
		logger.info("out of deleteEntrepriseById()");
		logger.debug("entreprise supprimer avec succées : ");
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		logger.info("In deleteDepartementById():");
		logger.debug("debut de delete departement:");
		Optional<Departement> departement =deptRepoistory.findById(depId);
		if(departement.isPresent()) {
			deptRepoistory.delete(departement.get());
		}
		
		logger.info("out of deleteDepartementById()");
		logger.debug("Departement supprimer avec succées : ");
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		logger.info("In getEntrepriseById():");
		
		try{
			logger.debug("debut recuperation entreprise:");
			
			Optional<Entreprise> entreprise = entrepriseRepoistory.findById(entrepriseId);
			if(entreprise.isPresent()) {
				logger.info("out of getEntrepriseById()");
				logger.debug("entreprise recuperé avec succées : ");
				return entreprise.get();
			}
			
		}catch(Exception e){
			logger.error(e);
		}
		return null;
	}

}

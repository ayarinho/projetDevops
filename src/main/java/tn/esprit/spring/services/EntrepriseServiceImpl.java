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

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	private static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {

		logger.debug("lancement  d'ajout d'une entreprise. ");

		entrepriseRepoistory.save(entreprise);

		logger.info("ajout terminé avec succée !!!");

		}catch (Exception e){
			logger.error("Erreur dans la méthode  ajouterEntreprise(): "+ e);
		}finally {
			logger.info("Méthode ajouterEntreprise() finie !!!!");
		}

		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {
			logger.debug("lancement d'ajout d'un deparetemnt. ");

			deptRepoistory.save(dep);

			logger.info("ajout terminé avec succée !!!");

		}catch (Exception e){
			logger.error("Erreur dans la méthode ajoutDepartement(): "+ e);
		}finally {
			logger.info("Méthode ajouterDepartement() finie !!!!");
		}

		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.

		try {

			logger.debug("lancement d'affectation d'un deparetemnt à une entreprise. ");

				Optional<Entreprise> valueEnt = entrepriseRepoistory.findById(entrepriseId);
				Optional<Departement> valueDep = deptRepoistory.findById(depId);

					if(valueEnt.isPresent() && valueDep.isPresent()) {

					Entreprise entrepriseManagedEntity=valueEnt.get();
					Departement depManagedEntity=valueDep.get();

					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					deptRepoistory.save(depManagedEntity);

					logger.info("le departement et bien affécté à l'entreprise !!");

					}

		}catch (Exception e){
			logger.error("Erreur dans la méthode affecterDepartementAEntreprise(): "+ e);
		}finally {
			logger.info("Méthode affecterDepartementAEntreprise() finie !!!!");
		}
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {


		Optional<Entreprise> valueEnt = entrepriseRepoistory.findById(entrepriseId);
		List<String> depNames = new ArrayList<>();

		try {

		if(valueEnt.isPresent()) {

			Entreprise entrepriseManagedEntity=valueEnt.get();

			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}

			logger.info("les departement de l'entrprise "+ valueEnt.get().getName()+ "sont"+ depNames);
		}

		} catch (Exception e) {
			logger.error("Erreur dans la méthode  getAllDepartementsNamesByEntreprise(): " + e);
		} finally {
			logger.info("Méthode getAllDepartementsNamesByEntreprise() finie !!!!");
		}

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		try {

			Optional<Entreprise> valueEnt=entrepriseRepoistory.findById(entrepriseId);
			if(valueEnt.isPresent()) {

				Entreprise ent=valueEnt.get();

				entrepriseRepoistory.delete(ent);

				logger.info(" l'Entreprise"+ ent.getName()+ " est supprimé ");
			}
		}catch (Exception e){
			logger.error("Erreur dans la méthode deleteEntrepriseById(): "+ e);
		} finally {
			logger.info("Méthode deleteEntreprise() finie !!!!");
		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		try {

			Optional<Departement> valueDep=deptRepoistory.findById(depId);

			if(valueDep.isPresent()) {

				Departement dep=valueDep.get();

				deptRepoistory.delete(dep);

				logger.info(" la  Departement " + dep.getName()+"est supprimé de la liste des departements");
			}
		}catch (Exception e){
			logger.error("Erreur dans la méthode  deleteDepartementById(): "+ e);
		} finally {
			logger.info("Méthode deleteDepartementById() finie !!!!");
		}

		}


	public Entreprise getEntrepriseById(int entrepriseId) {

		Entreprise entreprise = new Entreprise();

		try {
			Optional<Entreprise> valueEnt = entrepriseRepoistory.findById(entrepriseId);
			if(valueEnt.isPresent()) {

				entreprise=valueEnt.get();

				logger.info("Entreprise d'id "+entrepriseId+"est"+ entreprise );
			}
		} catch (Exception e) {
			logger.error("Erreur dans la méthode  getEntrepriseById(): " + e);
		} finally {
			logger.info("Méthode getEntrepriseById() finie !!!!");
		}

		return entreprise;
	}

}
package tn.esprit.spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {
	
	private static final Logger l = Logger.getLogger(DepartementServiceImpl.class);

	@Autowired
	DepartementRepository departementRepository;


	public List<Departement> getAllDepartements() {
		
		l.info("In getAllDepartements() : ");
		List<Departement> listDepartements=null;
		try {
		
	
			listDepartements = (List<Departement>) departementRepository.findAll();
			l.info("la liste des départements est"+listDepartements);
		}catch(Exception e) {
			l.error("il y une erreur"+e);
		}
		l.info("Out getAllDepartements() ");
		return listDepartements ; 
	}

}

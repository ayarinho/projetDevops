package tn.esprit.spring.dto;

import java.util.List;


import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

public class DepartementDto {

	
	
	//@JsonManagedReference 
	@JsonIgnore
	@ManyToMany
	private List<Employe> employes;
	
	@OneToMany(mappedBy="departement")
	private List<Mission> missions;
	
	@ManyToOne
	private Entreprise entreprise;


	
	
	

}
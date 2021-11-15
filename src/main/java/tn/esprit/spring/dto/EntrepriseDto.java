package tn.esprit.spring.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import tn.esprit.spring.entities.Departement;

public class EntrepriseDto {
	@OneToMany(mappedBy="entreprise", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<Departement> departements = new ArrayList<>();

	




	
	


}
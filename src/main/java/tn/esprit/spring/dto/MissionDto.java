package tn.esprit.spring.dto;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Timesheet;

public class MissionDto {

	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy="mission")
	private  List<Timesheet> timesheets;
	

	

}
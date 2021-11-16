package tn.esprit.spring.dto;



import java.util.Date;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ContratDto {

	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@OneToOne
	private EmployeDto employe;

	
}
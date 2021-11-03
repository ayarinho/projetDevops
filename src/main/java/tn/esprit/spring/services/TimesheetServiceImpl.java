package tn.esprit.spring.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	public static final Logger logger = Logger.getLogger(TimesheetServiceImpl.class);

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;

	//rayen
	public int ajouterMission(Mission mission) {
		logger.info("In ajouterMission():");
		logger.debug("debut d'ajout de la mission: " + mission.getName());
		missionRepository.save(mission);
		logger.info("out of ajouterMission()");
		logger.debug("la mission: " + mission.getName() + " de l'id: " + mission.getId() + " ajoutée avec succé");
		return mission.getId();
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		logger.info("In affecterMissionADepartement():");
		logger.debug("debut d'ajout de la mission: " + missionId );
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();
		mission.setDepartement(dep);
		missionRepository.save(mission);
		logger.info("out of affecterMissionADepartement()");
		logger.debug("la mission: " + missionId + " est affectée avec succé au département" + depId);
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		logger.info("In ajouterTimesheet():");
		logger.debug("debut d'ajout de TimeSheet: " + missionId + employeId + dateDebut + dateFin );
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		logger.info("out of ajouterTimesheet()");
		logger.debug("Le Timesheet" + missionId + employeId + dateDebut + dateFin + "est ajouté avec succès");
		
		
	}


	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		logger.info("In validerTimesheet():");
		logger.debug("debut de validation de timesheet: " + missionId + employeId + dateDebut + dateFin );
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		logger.info("out of validerTimesheet()");
		logger.debug("Le Timesheet" + missionId + employeId + dateDebut + dateFin + "est valide");
	}


	public List<Mission> findAllMissionByEmployeJPQL(int employeId) { 
		logger.info("In findAllMissionByEmployeJPQL():");
		logger.debug("debut de recherche des Missions de l'employe avec l'id: " + employeId  );
		List<Mission> missionss = new ArrayList<>();
		missionss=timesheetRepository.findAllMissionByEmployeJPQL(employeId);
		logger.info("out of findAllMissionByEmployeJPQL()");
		logger.debug("L'employe " + employeId + "à la liste" + missionss);
		return missionss;
		
		
		

	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		logger.info("In getAllEmployeByMission():");
		logger.debug("debut de recherche des employes par mission: " + missionId  );
		List<Employe> employés = new ArrayList<>();
		employés =timesheetRepository.getAllEmployeByMission(missionId);
		logger.info("out of getAllEmployeByMission()");
		logger.debug("Les employes " + employés + "sont affectés à la mission" + missionId);
		return employés;
	}

}

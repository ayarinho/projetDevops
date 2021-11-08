import org.junit.Test;
import org.junit.Assert;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.TimesheetServiceImpl;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={tn.esprit.spring.TimesheetSpringBootCoreDataJpaMvcRest1Application.class})

public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
    @Autowired
    private IEntrepriseService servEntreprise;

    @Autowired
    private IEmployeService servEmploye;

    @Autowired
    private EntrepriseRepository repEnt;

    @Autowired
    private EmployeRepository repEmp;

    @Autowired
    private DepartementRepository repDep;
    
    
    @Autowired
    private TimesheetServiceImpl servTimesheet;
    
    @Autowired
    private TimesheetRepository repTime;








    
    @Transactional
    @Test
    public void testAjouterMission(){
        Mission miss= new Mission("MissionTested2","telecommunications");
        Object isnull= null;
        		if(servTimesheet.ajouterMission(miss) != 0) 
        		{isnull="exist";}
        		Assert. assertNotNull(isnull) ;
    }
    
   

    @Transactional
    @Test
    public void testFindMissions(){
        
        Assert.assertNotNull(servTimesheet.findAllMissionByEmployeJPQL(1));
    }
    
    @Transactional
    @Test
    public void testFindEmployesByMission(){
        
        Assert.assertNotNull(servTimesheet.getAllEmployeByMission(1));
    }


}
import org.junit.Test;
import org.junit.Assert;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

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




    @Transactional
    @Test
    public void testAjouterEmploye() {
        Employe employe = new Employe("chbinou", "med firas", "medfiras.chbinou1@esprit.tn", true, Role.CHEF_DEPARTEMENT);
        int id = servEmploye.ajouterEmploye(employe);

        Assert.assertNotNull(repEmp.findById(id).get());
    }


    @Transactional
    @Test
    public void testMettreAjourEmailByEmployeId() {

        Assert.assertNotEquals(servEmploye.mettreAjourEmailByEmployeId("firas.firas@gmail.com", 1),0);

    }


    @Transactional
    @Test
    public void testAffecterEmployeADepartement() {
        Departement dep = new Departement("It");
        int id = servEntreprise.ajouterDepartement(dep);
        Assert.assertNotEquals(servEmploye.affecterEmployeADepartement(2, id),0);
    }

    @Transactional
    @Test
    public void testDesaffecterEmployeDuDepartement() {
        servEmploye.desaffecterEmployeDuDepartement(1, 1);
        Employe em = null;
        System.out.println(repDep.findById(1).get().getEmployes());
        for (Employe empl :
                repDep.findById(1).get().getEmployes()) {
            if (empl.getId() == 1) {
                em = empl;
            }
        }
        Assert.assertNull(em);
    }

    @Transactional
    @Test
    public void testAjouterContrat() {
        Contrat contrat = new Contrat("CDI", 1500);
        Assert.assertNotEquals(servEmploye.ajouterContrat(contrat),0);
    }


    @Transactional
    @Test
    public void testGetEmployePrenomById() {


        Assert.assertNotNull(repEmp.findById(1).get().getPrenom());
    }


    @Test
    public void testAffecterContratAEmploy() {
        servEmploye.affecterContratAEmploye(2, 1);
        Assert.assertNotNull(repEmp.findById(2).get().getContrat());
    }

    @Transactional
    @Test
    public void testAjouterEntreprise(){
        Entreprise ent= new Entreprise("telecom tunis","telelcomunication");
        int id=servEntreprise.ajouterEntreprise((ent));

        Assert.assertNotNull(repEnt.findById(id).get());
    }
    @Transactional
    @Test
    public void testajouterDepartement(){
    	
        Departement dep= new Departement("ressourse humaine");
        int id=servEntreprise.ajouterDepartement(dep);
        Assert.assertNotNull(repEnt.findById(id).get());
    }
    @Transactional
    @Test
    public void getAllDepartementsNamesByEntreprise(){
        
    	int entrepriseId=1;
        Assert.assertNotNull(servEntreprise.getAllDepartementsNamesByEntreprise(entrepriseId));
    }
    @Transactional
    @Test
    public void getEntrepriseById(){
        
    	int entrepriseId=1;
        Assert.assertNotNull(servEntreprise.getEntrepriseById(entrepriseId));
    }


}
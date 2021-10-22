import org.junit.Test;
import org.junit.Assert;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
    @Autowired
    private IEntrepriseService servEntreprise;

    @Autowired
    private EntrepriseRepository repEnt;

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
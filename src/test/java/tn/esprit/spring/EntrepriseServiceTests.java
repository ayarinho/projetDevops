package tn.esprit.spring;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import java.util.List;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceTests {

    @Autowired
    ControllerEntrepriseImpl entrepriseController;

    Integer a,b;

    @Test
    public void testAjouterEntreprise(){

        Entreprise ent=new Entreprise("esprit","Ghazala");
        a= entrepriseController.ajouterEntreprise(ent);
        assertNotNull(a);


    }

    @Test
    public void testGetEntrepriseById(){

        Entreprise ent= entrepriseController.getEntrepriseById(13);

        assertNotNull(ent);

    }

    @Test
    public void testAjouterDepartement(){

        Departement dep=new Departement("tech");
        b=entrepriseController.ajouterDepartement(dep);
        assertNotNull(b);
    }


    @Test
    public void testGetAllDepartementsNamesByEntreprise(){

        List<String> depNames= entrepriseController.getAllDepartementsNamesByEntreprise(15);
        assertNotNull(depNames);
    }

    @Test
    public void testAffecterDepartementAEntreprise(){

    	entrepriseController.affecterDepartementAEntreprise(26,15);

    }
    @Test
    public void testdeleteEntrepriseById(){

    	entrepriseController.deleteEntrepriseById(15);

    }

    @Test
    public void testdeleteDepartementById(){

    	entrepriseController.deleteEntrepriseById(26);

    }



}
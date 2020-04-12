package cl.prez.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cl.prez.hibernate.model.Band;
import cl.prez.hibernate.model.dto.BandDTO;

public class App {
    public static void main( String[] args ){
        // Name in persistence.xml
        final String PERSISTENCE_NAME = "persistence-unit";

        EntityManagerFactory entityManagerFactory   = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        EntityManager entityManager                 = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("FROM Band", Band.class);
        List<Band> bands = query.getResultList();

        System.out.println("Bands 1");
        for(Band band : bands){
            System.out.println(band);
        }
        
        query = entityManager.createNamedQuery("getInfoBands");
        List<BandDTO> bandDTOs = query.getResultList();

        System.out.println("Bands 2");
        for(BandDTO band : bandDTOs){
            System.out.println(band);
        }
    }
}

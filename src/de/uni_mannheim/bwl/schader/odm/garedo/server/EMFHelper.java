package de.uni_mannheim.bwl.schader.odm.garedo.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFHelper {
	
    private static final String PERSISTENCE_UNIT = "GaredoPCR";
	private static EntityManagerFactory emf;
    
    public static EntityManagerFactory getFactory() {
    	
    	if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT); 
            } catch(ExceptionInInitializerError e) {
                System.err.println("Couldn't get EntityManagerFactory\n" + e);
            }
    	}
    	
        return emf;
    }
    
    public static void closeFactory() {
    	
    	if (emf != null) {
            emf.close();
            emf = null;
        }
    	
    }
}

package de.uni_mannheim.bwl.schader.odm.garedo.server;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.exceptions.DatabaseException;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Profile;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Project;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.ProjectDTO;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.UserDTO;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.ProjectService;
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.UserService;
import de.uni_mannheim.bwl.schader.odm.garedo.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProjectServiceImpl extends RemoteServiceServlet implements
		ProjectService {

	//--------------//
	// Project CRUD //
	//--------------//
	public int createProject(String name) throws IllegalArgumentException {
		Project project = getProjectByName(name);
		if(project != null) {
			throw new IllegalArgumentException("ERROR: project already exists");
		} else {
			EntityManager em = EMFHelper.getFactory().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			try {
				tx.begin();
				project = new Project(name);
				em.persist(project);
				tx.commit();
			} catch(DatabaseException e) {
				//TODO: improve exception handling, should not depend on exception but be sanitized before
				tx.rollback();
				throw new IllegalArgumentException(e);
			} finally {
				em.close();
			}
		}
		
		return project.getId();
	}
	
	//-------------------//
	// Private Functions //
	//-------------------//

	private Project getProjectByName(String name) {
		Project project = null;
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		TypedQuery<Project> query = em.createNamedQuery("getProjectByName",Project.class);
		query.setParameter("name", name);
		try {
			project = query.getSingleResult();
		} catch(NoResultException e) {
			// allright, no result, return null
		} finally {
			em.close();
		}
		return project;
	}	
}
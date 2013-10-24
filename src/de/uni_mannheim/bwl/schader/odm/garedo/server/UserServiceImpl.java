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
import de.uni_mannheim.bwl.schader.odm.garedo.client.services.UserService;
import de.uni_mannheim.bwl.schader.odm.garedo.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {

//	public String greetServer(String input) throws IllegalArgumentException {
//		// Verify that the input is valid. 
//		if (!FieldVerifier.isValidName(input)) {
//			// If the input is not valid, throw an IllegalArgumentException back to
//			// the client.
//			throw new IllegalArgumentException(
//					"Name must be at least 4 characters long");
//		}
//
//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//
//		// Escape data from the client to avoid cross-site script vulnerabilities.
//		input = escapeHtml(input);
//		userAgent = escapeHtml(userAgent);
//
//		return "Hello, " + input + "!<br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
//	}
	
	//-----------//
	// User CRUD //
	//-----------//
	
	public UserDTO loadUser(int id) throws IllegalArgumentException {
		User user = getUserById(id);
		
		if(user == null) {
			//TODO: use specific exception
			throw new IllegalArgumentException("ERROR: user does not exist");
		} else {
			return new UserDTO(user);
		}
	}
	
	public UserDTO loadUser(String name) throws IllegalArgumentException {
		User user = getUserByName(name);
		
		if(user == null) {
			//TODO: use specific exception
			throw new IllegalArgumentException("ERROR: user does not exist");
		} else {
			return new UserDTO(user);
		}
	}
	
	public int createUser(String name) throws IllegalArgumentException {
		User user = getUserByName(name);
		if(user != null) {
			throw new IllegalArgumentException("ERROR: user already exists");
		} else {
			EntityManager em = EMFHelper.getFactory().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			try {
				tx.begin();
				user = new User(name);
				em.persist(user);
				tx.commit();
			} catch(DatabaseException e) {
				//TODO: improve exception handling, should not depend on exception but be sanitized before
				tx.rollback();
				throw new IllegalArgumentException(e);
			} finally {
				em.close();
			}
		}
		
		return user.getId();
	}
	
	public void updateUser(User user) throws IllegalArgumentException {
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch(EntityExistsException e) {
			//TODO: improve exception handling
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	public void deleteUser(int id) throws IllegalArgumentException {
		User user = getUserById(id);
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.remove(user);
			tx.commit();
		} catch(EntityExistsException e) {
			//TODO: improve exception handling
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	//--------------//
	// Profile  RU  //
	//--------------//
	
	public Profile loadProfile(int id) throws IllegalArgumentException {
		return getProfileById(id);
	}
	
	public void updateProfile(Profile profile) throws IllegalArgumentException {
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Profile persistentProfile = em.find(Profile.class, profile.getId());
		
		try {
			tx.begin();
			persistentProfile.setBirthDate(profile.getBirthDate());
			persistentProfile.setQualifications(profile.getQualifications());
			em.persist(persistentProfile);
			tx.commit();
		} catch(EntityExistsException e) {
			//TODO: improve exception handling
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	//--------------//
	// Project CR   //
	//--------------//
	public void addProject(int userId, int projectId) throws IllegalArgumentException {
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		User user = em.find(User.class, userId);
		Project project = em.find(Project.class, projectId);
		if(user == null || project == null) {
			//TODO: improve exception handling
			throw new IllegalArgumentException("ERROR: Either user id or project id wrong");
		} else {
			
			try {
				tx.begin();
				user.addProject(project);
				em.persist(user);
				tx.commit();
			} catch(EntityExistsException e) {
				//TODO: improve exception handling
				tx.rollback();
				throw e;
			} finally {
				em.close();
			}
			
		}
	}
	
	public Set<ProjectDTO> loadProjects(int userId) throws IllegalArgumentException {
		Set<ProjectDTO> projectSet = new HashSet<ProjectDTO>();
		User user = getUserById(userId);
		for(Project project : user.getProjects()) {
			projectSet.add(new ProjectDTO(project));
		}
		return projectSet;
	}
	
	//-------------------//
	// Private Functions //
	//-------------------//
	
	private User getUserById(int id) {
		//TODO: change to em.find
		User user = null;
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("getUserById",User.class);
		query.setParameter("id", id);
		try {
			user = query.getSingleResult();
		} catch(NoResultException e) {
			// allright, no result, return null
		} finally {
			em.close();
		}
		return user;
	}	
	
	private User getUserByName(String name) {
		User user = null;
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("getUserByName",User.class);
		query.setParameter("name", name);
		try {
			user = query.getSingleResult();
		} catch(NoResultException e) {
			// allright, no result, return null
		} finally {
			em.close();
		}
		return user;
	}	
	
	private Profile getProfileById(int id) {
		//TODO: change to em.find
		Profile profile = null;
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		TypedQuery<Profile> query = em.createNamedQuery("getProfileById",Profile.class);
		query.setParameter("id", id);
		try {
			profile = query.getSingleResult();
		} catch(NoResultException e) {
			//TODO: maybe improve exception handling
			throw e;
		} finally {
			em.close();
		}
		return profile;
	}
	
}

//	/**
//	 * Escape an html string. Escaping data received from the client helps to
//	 * prevent cross-site script vulnerabilities.
//	 * 
//	 * @param html the html string to escape
//	 * @return the escaped string
//	 */
//	private String escapeHtml(String html) {
//		if (html == null) {
//			return null;
//		}
//		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
//				.replaceAll(">", "&gt;");
//	}

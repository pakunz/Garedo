package de.uni_mannheim.bwl.schader.odm.garedo.server;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.exceptions.DatabaseException;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
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
	
	public User loadUser(String name) throws IllegalArgumentException {
		User user = getUserByName(name);
		if(user == null) {
			//TODO: use specific exception
			throw new IllegalArgumentException("ERROR: user does not exist");
		}
		return user;
	}
	
	public User createUser(String name) throws IllegalArgumentException {
		
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
		
		return user;
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
	
	public void deleteUser(User user) throws IllegalArgumentException {
		EntityManager em = EMFHelper.getFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			user = em.merge(user);
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
}

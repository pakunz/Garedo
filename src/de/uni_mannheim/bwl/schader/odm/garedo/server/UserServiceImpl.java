package de.uni_mannheim.bwl.schader.odm.garedo.server;

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
		//TODO
		User user = null;
		return user;
	}
	
	public User createUser(String name) throws IllegalArgumentException {
		//TODO
		User user = null;
		return user;
	}
	
	public void updateUser(User user) throws IllegalArgumentException {
		//TODO
	}
	
	public void deleteUser(User user) throws IllegalArgumentException {
		//TODO
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

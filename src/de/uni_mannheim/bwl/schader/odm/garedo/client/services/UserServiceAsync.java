package de.uni_mannheim.bwl.schader.odm.garedo.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UserServiceAsync {
	
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	//-----------//
	// User CRUD //
	//-----------//
	void createUser(String name, AsyncCallback<User> callback)
			throws IllegalArgumentException;
	
	void loadUser(String name, AsyncCallback<User> callback)
			throws IllegalArgumentException;
	
	void updateUser(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	void deleteUser(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	
}

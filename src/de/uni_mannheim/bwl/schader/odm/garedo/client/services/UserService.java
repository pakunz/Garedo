package de.uni_mannheim.bwl.schader.odm.garedo.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	
//	//TODO: remove
//	String greetServer(String name) throws IllegalArgumentException;
	
	//-----------//
	// User CRUD //
	//-----------//
	User createUser(String name) throws IllegalArgumentException;
	
	User loadUser(String name) throws IllegalArgumentException;
	
	void updateUser(User user) throws IllegalArgumentException;
	
	void deleteUser(User user) throws IllegalArgumentException;
	
}

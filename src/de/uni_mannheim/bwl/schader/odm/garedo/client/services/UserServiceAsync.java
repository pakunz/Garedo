package de.uni_mannheim.bwl.schader.odm.garedo.client.services;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Profile;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.ProjectDTO;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.UserDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UserServiceAsync {
	
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	//-----------//
	// User CRUD //
	//-----------//
	void createUser(String name, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
	
	void loadUser(int id, AsyncCallback<UserDTO> callback)
			throws IllegalArgumentException;
	
	void loadUser(String name, AsyncCallback<UserDTO> callback)
			throws IllegalArgumentException;
	
	void updateUser(User user, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	void deleteUser(int id, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	//--------------//
	// Profile  RU  //
	//--------------//
	void loadProfile(int id, AsyncCallback<Profile> callback)
			throws IllegalArgumentException;
	
	void updateProfile(Profile profile, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	//--------------//
	// Project CR   //
	//--------------//
	void addProject(int userId, int projectId, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	
	void loadProjects(int userId, AsyncCallback<Set<ProjectDTO>> callback)
			throws IllegalArgumentException;
	
}

package de.uni_mannheim.bwl.schader.odm.garedo.client.services;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Profile;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.ProjectDTO;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO.UserDTO;

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
	int createUser(String name) throws IllegalArgumentException;
	
	UserDTO loadUser(int id) throws IllegalArgumentException;
	
	UserDTO loadUser(String name) throws IllegalArgumentException;
	
	void updateUser(User user) throws IllegalArgumentException;
	
	void deleteUser(int id) throws IllegalArgumentException;
	
	//--------------//
	// Profile  RU  //
	//--------------//
	Profile loadProfile(int id) throws IllegalArgumentException;
	
	void updateProfile(Profile profile) throws IllegalArgumentException;
	
	//--------------//
	// Project CR   //
	//--------------//
	void addProject(int userId, int projectId) throws IllegalArgumentException;
	
	Set<ProjectDTO> loadProjects() throws IllegalArgumentException;
	
	Set<ProjectDTO> loadProjects(int userId) throws IllegalArgumentException;
	
}

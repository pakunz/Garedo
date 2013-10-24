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
public interface ProjectServiceAsync {

	//-----------//
	// User CRUD //
	//-----------//
	void createProject(String name, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
	
}

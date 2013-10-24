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
@RemoteServiceRelativePath("project")
public interface ProjectService extends RemoteService {
	
	//--------------//
	// Project CRUD //
	//--------------//
	int createProject(String name) throws IllegalArgumentException;
	
}

package de.uni_mannheim.bwl.schader.odm.garedo.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface UserService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	
	
}

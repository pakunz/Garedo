package de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Project;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;

public class ProjectDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 10013L;
	
	private int id;
	private String name;
	private Set<Integer> userIds = new HashSet<Integer>();
	
	//--------------//
	// Constructors //
	//--------------//
	
	public ProjectDTO() {}
	
	public ProjectDTO(Project project) {
		setId(project.getId());
		setName(project.getName());
		setUserIds(project.getUsers());
	}
	
	
	//---------------------//
	// Getters and Setters //
	//---------------------//
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<User> users) {
		for(User user : users) {
			this.userIds.add(user.getId());
		}
	}
	
	
	
}

package de.uni_mannheim.bwl.schader.odm.garedo.client.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Profile;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.Project;
import de.uni_mannheim.bwl.schader.odm.garedo.client.model.User;

public class UserDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 10042L;
	
	private int id;
	private String name;
	private int profileId;
	private Set<Integer> projectIds = new HashSet<Integer>();
	
	//--------------//
	// Constructors //
	//--------------//
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		setId(user.getId());
		setName(user.getName());
		setProfileId(user.getProfile().getId());
		setProjectIds(user.getProjects());
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

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	public Set<Integer> getProjectIds() {
		return projectIds;
	}
	
	public void setProjectIds(Set<Project> projects) {
		for(Project project : projects) {
			this.projectIds.add(project.getId());
		}
	}
	
}

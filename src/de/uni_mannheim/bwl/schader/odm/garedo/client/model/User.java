package de.uni_mannheim.bwl.schader.odm.garedo.client.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Entity
@NamedQuery(
	name = "getUserByName",
	query = "SELECT u FROM User u WHERE u.name = :name"
)
public class User implements Serializable, IsSerializable {

	private static final long serialVersionUID = 42L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Project> projects;
	
	//--------------//
	// Constructors //
	//--------------//
	
	public User() {
		super();
		this.projects = new HashSet<Project>();
	}

	public User(String name) {
		this();
		setName(name);
	}
	
	//---------------------//
	// Getters and Setters //
	//---------------------//
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public Set<Project> getProjects() {
		return projects;
	}
	
	public void addProject(Project project) {
		this.projects.add(project);
		if(!project.getUsers().contains(this)) {
			project.addUser(this);
		}
	}
	
	public void removeProject(Project project) {
		this.projects.remove(project);
		if(project.getUsers().contains(this)) {
			project.removeUser(this);
		}
	}
	
}

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "getProjectByName",
		query = "SELECT p FROM Project p WHERE p.name = :name"
	),
	@NamedQuery(
		name = "getAllProjects",
		query = "SELECT p FROM Project p"
	)
})
public class Project implements Serializable, IsSerializable  {

	private static final long serialVersionUID = 13L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "projects", cascade = CascadeType.PERSIST)
	private Set<User> users = new HashSet<User>();

	//--------------//
	// Constructors //
	//--------------//
	
	public Project() {
		//super();
	}
	
	public Project(String name) {
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

	public int getId() {
		return id;
	}

	public Set<User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
		if(!user.getProjects().contains(this)) {
			user.addProject(this);
		}
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
		if(user.getProjects().contains(this)) {
			user.removeProject(this);
		}
	}
	
}

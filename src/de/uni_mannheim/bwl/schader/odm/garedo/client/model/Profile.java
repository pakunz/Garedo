package de.uni_mannheim.bwl.schader.odm.garedo.client.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Profile implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1337L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(mappedBy = "profile")
	private User user;
	
	private Date birthDate;
	private List<String> qualifications = new ArrayList<String>();
	
	//--------------//
	// Constructors //
	//--------------//
	
	public Profile() {
		//super();
	}

	//---------------------//
	// Getters and Setters //
	//---------------------//
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public List<String> getQualifications() {
		return qualifications;
	}
	
	public void addQualification(String qualification) {
		this.qualifications.add(qualification);
	}
	
	public void removeQualification(String qualification) {
		backLoop: for(int i=qualifications.size()-1; i>=0; i--) {
			if(qualifications.get(i).equals(qualification)) {
				qualifications.remove(i);
				break backLoop;
			}
		}
	}

}

package com.project.repository;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KickOffSignature  {
	@Id
	@UuidGenerator
	private String id;
	private String kickOffId;
	private String departments;
	private String headName;
	
	
	
	public KickOffSignature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KickOffSignature(String id, String kickOffId, String departments, String headName) {
		super();
		this.id = id;
		this.kickOffId = kickOffId;
		this.departments = departments;
		this.headName = headName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKickOffId() {
		return kickOffId;
	}
	public void setKickOffId(String kickOffId) {
		this.kickOffId = kickOffId;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	
	
	

}

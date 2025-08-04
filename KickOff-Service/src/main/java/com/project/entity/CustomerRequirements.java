package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerRequirements {
	
	@Id
	@UuidGenerator
    private String requirementId;
	private String kickOffId;
	private String companyId;
	private String employeeId;
	private String requirementType;
	private String requirementOne;
	private String requirementTwo;
	private String requirementThree;
	private String requirementFour;

	
	
	


	 public CustomerRequirements(String requirementId, String kickOffId, String companyId, String employeeId,
			String requirementType, String requirementOne, String requirementTwo, String requirementThree,
			String requirementFour) {
		super();
		this.requirementId = requirementId;
		this.kickOffId = kickOffId;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.requirementType = requirementType;
		this.requirementOne = requirementOne;
		this.requirementTwo = requirementTwo;
		this.requirementThree = requirementThree;
		this.requirementFour = requirementFour;
	}

	
	 
	 public CustomerRequirements() {
		super();
		// TODO Auto-generated constructor stub
	}



	 public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getKickOffId() {
		return kickOffId;
	}

	public void setKickOffId(String kickOffId) {
		this.kickOffId = kickOffId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getRequirementType() {
		return requirementType;
	}

	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}

	public String getRequirementOne() {
		return requirementOne;
	}

	public void setRequirementOne(String requirementOne) {
		this.requirementOne = requirementOne;
	}

	public String getRequirementTwo() {
		return requirementTwo;
	}

	public void setRequirementTwo(String requirementTwo) {
		this.requirementTwo = requirementTwo;
	}

	public String getRequirementThree() {
		return requirementThree;
	}

	public void setRequirementThree(String requirementThree) {
		this.requirementThree = requirementThree;
	}

	public String getRequirementFour() {
		return requirementFour;
	}

	public void setRequirementFour(String requirementFour) {
		this.requirementFour = requirementFour;
	}
	
	



}

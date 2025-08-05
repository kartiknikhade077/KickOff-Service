package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CheckListCategory {

	
	@Id
	@UuidGenerator
	private String categoryId;
	private String categoryName;
	private String companyId;
	private int sequence;
	
	
	
	public CheckListCategory(String categoryId, String categoryName, String companyId, int sequence) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.companyId = companyId;
		this.sequence = sequence;
	}
	public CheckListCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
	
}

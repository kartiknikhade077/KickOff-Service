package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BOMCategory {
	
	@Id
	@UuidGenerator
	private String categoryId;
	private String companyId;
	private String categoryType;
	private String categoryField;
	private boolean status;
	public BOMCategory(String categoryId, String companyId, String categoryType, String categoryField) {
		super();
		this.categoryId = categoryId;
		this.companyId = companyId;
		this.categoryType = categoryType;
		this.categoryField = categoryField;
	}
	public BOMCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryField() {
		return categoryField;
	}
	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
	

}

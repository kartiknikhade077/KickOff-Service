package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CheckListCategoryAndItems {

	@Id
	@UuidGenerator
	private String itemId;
	private String companyId;
	private String categoryType;
	private String checkListItem;
	private int sequence;
	
	
	
	public CheckListCategoryAndItems(String itemId, String companyId, String categoryType, String checkListItem) {
		super();
		this.itemId = itemId;
		this.companyId = companyId;
		this.categoryType = categoryType;
		this.checkListItem = checkListItem;
	}
	
	
	
	public CheckListCategoryAndItems() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	public String getCheckListItem() {
		return checkListItem;
	}
	public void setCheckListItem(String checkListItem) {
		this.checkListItem = checkListItem;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}

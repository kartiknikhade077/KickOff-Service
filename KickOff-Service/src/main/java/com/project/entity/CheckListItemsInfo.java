package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CheckListItemsInfo {
	@Id
	@UuidGenerator
	private String checkListItemId;
	private String checkListId;
	private String categoryType;
	private String checkListItem;
	private String checkByDesigner;
	private String checkByTeamLead;
	
	
	
	public CheckListItemsInfo(String checkListItemId, String checkListId, String categoryType, String checkListItem,
			String checkByDesigner, String checkByTeamLead) {
		super();
		this.checkListItemId = checkListItemId;
		this.checkListId = checkListId;
		this.categoryType = categoryType;
		this.checkListItem = checkListItem;
		this.checkByDesigner = checkByDesigner;
		this.checkByTeamLead = checkByTeamLead;
	}
	
	
	
	public CheckListItemsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getCheckListItemId() {
		return checkListItemId;
	}
	public void setCheckListItemId(String checkListItemId) {
		this.checkListItemId = checkListItemId;
	}
	public String getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
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
	public String getCheckByDesigner() {
		return checkByDesigner;
	}
	public void setCheckByDesigner(String checkByDesigner) {
		this.checkByDesigner = checkByDesigner;
	}
	public String getCheckByTeamLead() {
		return checkByTeamLead;
	}
	public void setCheckByTeamLead(String checkByTeamLead) {
		this.checkByTeamLead = checkByTeamLead;
	}
	
	
	

}

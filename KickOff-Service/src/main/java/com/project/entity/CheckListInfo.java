package com.project.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CheckListInfo {
	
	@Id
	@UuidGenerator
	private String checkListId;
	private String companyId;
	private String employeeId;
	private String designerName;
	private String customerName;
	private String projectName;
	private String workOrderNumber;
	private String revisionNumber;
	private String partDetials;              // will save the wo process info
	private String toolDetails;
	private LocalDateTime createdDateTime;
	private Date designStartDate;
	private Date designEndDate;
	private Date pocketMCReleaseDate;
	private Date finalMCReleaseDate;
	
	public CheckListInfo(String checkListId, String companyId, String employeeId, String designerName,
			String customerName, String projectName, String workOrderNumber, String revisionNumber, String partDetials,
			String toolDetails) {
		super();
		this.checkListId = checkListId;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.designerName = designerName;
		this.customerName = customerName;
		this.projectName = projectName;
		this.workOrderNumber = workOrderNumber;
		this.revisionNumber = revisionNumber;
		this.partDetials = partDetials;
		this.toolDetails = toolDetails;
	}
	public CheckListInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
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
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getCustomerName() {
		return customerName;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}
	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}
	public String getRevisionNumber() {
		return revisionNumber;
	}
	public void setRevisionNumber(String revisionNumber) {
		this.revisionNumber = revisionNumber;
	}
	public String getPartDetials() {
		return partDetials;
	}
	public void setPartDetials(String partDetials) {
		this.partDetials = partDetials;
	}
	public String getToolDetails() {
		return toolDetails;
	}
	public void setToolDetails(String toolDetails) {
		this.toolDetails = toolDetails;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getDesignStartDate() {
		return designStartDate;
	}
	public void setDesignStartDate(Date designStartDate) {
		this.designStartDate = designStartDate;
	}
	public Date getDesignEndDate() {
		return designEndDate;
	}
	public void setDesignEndDate(Date designEndDate) {
		this.designEndDate = designEndDate;
	}
	public Date getPocketMCReleaseDate() {
		return pocketMCReleaseDate;
	}
	public void setPocketMCReleaseDate(Date pocketMCReleaseDate) {
		this.pocketMCReleaseDate = pocketMCReleaseDate;
	}
	public Date getFinalMCReleaseDate() {
		return finalMCReleaseDate;
	}
	public void setFinalMCReleaseDate(Date finalMCReleaseDate) {
		this.finalMCReleaseDate = finalMCReleaseDate;
	}

	

}

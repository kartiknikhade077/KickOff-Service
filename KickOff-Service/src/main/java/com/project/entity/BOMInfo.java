package com.project.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BOMInfo {
    @Id
    @UuidGenerator
	private String bomId;
    private String companyId;
    private String customerName;
    private String projectName;
    private String partName;
    private int itemNo;
    private String workOrderNo;
    private String projectDetails;
    private Date bomDate;
    private String revisionNumber;
    private String dieDetails;
    private LocalDateTime createdDateTime;
    
    
	public BOMInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BOMInfo(String bomId, String companyId, String customerName, String projectName, String partName, int itemNo,
			String workOrderNo, String projectDetails, Date bomDate, String revisionNumber, String dieDetails) {
		super();
		this.bomId = bomId;
		this.companyId = companyId;
		this.customerName = customerName;
		this.projectName = projectName;
		this.partName = partName;
		this.itemNo = itemNo;
		this.workOrderNo = workOrderNo;
		this.projectDetails = projectDetails;
		this.bomDate = bomDate;
		this.revisionNumber = revisionNumber;
		this.dieDetails = dieDetails;
	}
	public String getBomId() {
		return bomId;
	}
	public void setBomId(String bomId) {
		this.bomId = bomId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getWorkOrderNo() {
		return workOrderNo;
	}
	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	public String getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}
	public Date getBomDate() {
		return bomDate;
	}
	public void setBomDate(Date bomDate) {
		this.bomDate = bomDate;
	}
	public String getRevisionNumber() {
		return revisionNumber;
	}
	public void setRevisionNumber(String revisionNumber) {
		this.revisionNumber = revisionNumber;
	}
	public String getDieDetails() {
		return dieDetails;
	}
	public void setDieDetails(String dieDetails) {
		this.dieDetails = dieDetails;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
    
    
	
}

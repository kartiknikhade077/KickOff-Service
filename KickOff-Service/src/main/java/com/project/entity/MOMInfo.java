package com.project.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import com.github.f4b6a3.uuid.UuidCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class MOMInfo {
	
	@Id
	private String momId;
	private String companyId;
	private String employeeeId;
	private String customerName;
	private String venue;
	private String contactPersonName;
	private String employeeName;
	private String projectName;
	private String itemNo;
	private Date createdDate;
	private String introduction;
	private String thirdCompany;
	private String thirdPersonCompany;
	private String remark;
	private String projectId;
	private String customerId;
	
	public MOMInfo(String momId, String companyId, String employeeeId,
			String customerName, String venue, String contactPersonName, String employeeName, String projectName,
			String itemNo, Date createdDate, String introduction, String thirdCompany, String thirdPersonCompany) {
		super();
		this.momId = momId;
		this.companyId = companyId;
		this.employeeeId = employeeeId;
		this.customerName = customerName;
		this.venue = venue;
		this.contactPersonName = contactPersonName;
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.itemNo = itemNo;
		this.createdDate = createdDate;
		this.introduction = introduction;
		this.thirdCompany = thirdCompany;
		this.thirdPersonCompany = thirdPersonCompany;
	}
	public MOMInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMomId() {
		return momId;
	}
	public void setMomId(String momId) {
		this.momId = momId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getEmployeeeId() {
		return employeeeId;
	}
	public void setEmployeeeId(String employeeeId) {
		this.employeeeId = employeeeId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getThirdCompany() {
		return thirdCompany;
	}
	public void setThirdCompany(String thirdCompany) {
		this.thirdCompany = thirdCompany;
	}
	public String getThirdPersonCompany() {
		return thirdPersonCompany;
	}
	public void setThirdPersonCompany(String thirdPersonCompany) {
		this.thirdPersonCompany = thirdPersonCompany;
	}
	
	 public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	@PrePersist
	    public void prePersist() {
	        if (momId == null) {
	        	momId = UuidCreator.getTimeOrderedEpoch().toString();  // UUIDv7-style sortable UUID
	        }
	    }

	
	

}

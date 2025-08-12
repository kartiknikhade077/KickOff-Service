package com.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KickOff {
	
	@Id
	@UuidGenerator
	private String kickOffId;
	private String companyId;
	private String employeeid;
	private String projectId;
	private String customerName;
	private String contactPersonName;
	private String mobileNumber;
	private String companyWebsite;
	private String billingAddress;
	private String shippingAddress;
	private String projectName;
	private String projectTitle;
	private LocalDate kickOffDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createdDateTime;
	private String customerId;
	
	
	public KickOff(String kickOffId, String companyId, String employeeid, String customerName, String contactPersonName,
			String mobileNumber, String companyWebsite, String billingAddress, String shippingAddress,
			String projectName, String projectTitle, LocalDate kickOffDate, LocalDate startDate, LocalDate endDate) {
		super();
		this.kickOffId = kickOffId;
		this.companyId = companyId;
		this.employeeid = employeeid;
		this.customerName = customerName;
		this.contactPersonName = contactPersonName;
		this.mobileNumber = mobileNumber;
		this.companyWebsite = companyWebsite;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.projectName = projectName;
		this.projectTitle = projectTitle;
		this.kickOffDate = kickOffDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public KickOff() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public LocalDate getKickOffDate() {
		return kickOffDate;
	}
	public void setKickOffDate(LocalDate kickOffDate) {
		this.kickOffDate = kickOffDate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	

}

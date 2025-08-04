package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ItemProcess {
    @Id
    @UuidGenerator
	private String partProcessId;
    private String kickOffId;
    private int itemNo;
    private String workOrderNumber;
    private String designerName;
    private String employeeId;
    private String process;
    private double length;
    private double height;
    private double width;
    private String remarks;
	public ItemProcess(String partProcessId, String kickOffId, int itemNo, String workOrderNumber, String designerName,
			String employeeId, String process, double length, double height, double width, String remarks) {
		super();
		this.partProcessId = partProcessId;
		this.kickOffId = kickOffId;
		this.itemNo = itemNo;
		this.workOrderNumber = workOrderNumber;
		this.designerName = designerName;
		this.employeeId = employeeId;
		this.process = process;
		this.length = length;
		this.height = height;
		this.width = width;
		this.remarks = remarks;
	}
	public ItemProcess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPartProcessId() {
		return partProcessId;
	}
	public void setPartProcessId(String partProcessId) {
		this.partProcessId = partProcessId;
	}
	public String getKickOffId() {
		return kickOffId;
	}
	public void setKickOffId(String kickOffId) {
		this.kickOffId = kickOffId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getWorkOrderNumber() {
		return workOrderNumber;
	}
	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
    
}

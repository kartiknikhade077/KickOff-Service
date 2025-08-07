package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BOMCategoryInfo {
	
	@Id
    @UuidGenerator
    private String BOMCategoryInfoId;
	private String bomId;
	private int itemNo;
	private String itemDescription;
	private String malt;
	private double finishSizeLength;
	private double finishSizeHeight;
	private double finishSizeWidth;
	private double rawSizeLength;
	private double rawSizeHeight;
	private double rawSizeWidth;
	private int qunatity;
	private String remarks;
	private double modelWeight;
	private String orderingRemarks;
	private String boughtOutItems;
	private int boughtOutQuantity;
	private String specification;
	private String section;
	
	
	public BOMCategoryInfo(String bOMCategoryInfoId, String bomId, int itemNo, String itemDescription, String malt,
			double finishSizeLength, double finishSizeHeight, double finishSizeWidth, double rawSizeLength,
			double rawSizeHeight, double rawSizeWidth, int qunatity, String remarks, double modelWeight,
			String orderingRemarks, String boughtOutItems, int boughtOutQuantity, String specification,
			String section) {
		super();
		BOMCategoryInfoId = bOMCategoryInfoId;
		this.bomId = bomId;
		this.itemNo = itemNo;
		this.itemDescription = itemDescription;
		this.malt = malt;
		this.finishSizeLength = finishSizeLength;
		this.finishSizeHeight = finishSizeHeight;
		this.finishSizeWidth = finishSizeWidth;
		this.rawSizeLength = rawSizeLength;
		this.rawSizeHeight = rawSizeHeight;
		this.rawSizeWidth = rawSizeWidth;
		this.qunatity = qunatity;
		this.remarks = remarks;
		this.modelWeight = modelWeight;
		this.orderingRemarks = orderingRemarks;
		this.boughtOutItems = boughtOutItems;
		this.boughtOutQuantity = boughtOutQuantity;
		this.specification = specification;
		this.section = section;
	}
	public BOMCategoryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBOMCategoryInfoId() {
		return BOMCategoryInfoId;
	}
	public void setBOMCategoryInfoId(String bOMCategoryInfoId) {
		BOMCategoryInfoId = bOMCategoryInfoId;
	}
	public String getBomId() {
		return bomId;
	}
	public void setBomId(String bomId) {
		this.bomId = bomId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getMalt() {
		return malt;
	}
	public void setMalt(String malt) {
		this.malt = malt;
	}
	public double getFinishSizeLength() {
		return finishSizeLength;
	}
	public void setFinishSizeLength(double finishSizeLength) {
		this.finishSizeLength = finishSizeLength;
	}
	public double getFinishSizeHeight() {
		return finishSizeHeight;
	}
	public void setFinishSizeHeight(double finishSizeHeight) {
		this.finishSizeHeight = finishSizeHeight;
	}
	public double getFinishSizeWidth() {
		return finishSizeWidth;
	}
	public void setFinishSizeWidth(double finishSizeWidth) {
		this.finishSizeWidth = finishSizeWidth;
	}
	public double getRawSizeLength() {
		return rawSizeLength;
	}
	public void setRawSizeLength(double rawSizeLength) {
		this.rawSizeLength = rawSizeLength;
	}
	public double getRawSizeHeight() {
		return rawSizeHeight;
	}
	public void setRawSizeHeight(double rawSizeHeight) {
		this.rawSizeHeight = rawSizeHeight;
	}
	public double getRawSizeWidth() {
		return rawSizeWidth;
	}
	public void setRawSizeWidth(double rawSizeWidth) {
		this.rawSizeWidth = rawSizeWidth;
	}
	public int getQunatity() {
		return qunatity;
	}
	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getModelWeight() {
		return modelWeight;
	}
	public void setModelWeight(double modelWeight) {
		this.modelWeight = modelWeight;
	}
	public String getOrderingRemarks() {
		return orderingRemarks;
	}
	public void setOrderingRemarks(String orderingRemarks) {
		this.orderingRemarks = orderingRemarks;
	}
	public String getBoughtOutItems() {
		return boughtOutItems;
	}
	public void setBoughtOutItems(String boughtOutItems) {
		this.boughtOutItems = boughtOutItems;
	}
	public int getBoughtOutQuantity() {
		return boughtOutQuantity;
	}
	public void setBoughtOutQuantity(int boughtOutQuantity) {
		this.boughtOutQuantity = boughtOutQuantity;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
	
}

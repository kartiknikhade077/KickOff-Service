package com.project.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class KickOffItems {

	@Id
	@UuidGenerator
	private String itemId;
	private String kickOffId;
    private int itemNo;
    private String partName;
    private String material;
    private String thickness;
    @Transient
    private List<String> imageList;
    
	public KickOffItems(String itemId, String kickOffId, int itemNo, String partName, String material,
			String thickness) {
		super();
		this.itemId = itemId;
		this.kickOffId = kickOffId;
		this.itemNo = itemNo;
		this.partName = partName;
		this.material = material;
		this.thickness = thickness;
	}
	
	
	public KickOffItems() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
    
	
    
    
    
}

package com.project.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class MOMEntriesImages {
	@Id
	@UuidGenerator
	private String imagesId;
	private String momEntryId;
	@Lob
    @Column(columnDefinition = "LONGBLOB")
	private byte[] image;
	private String type;
	
	
	public MOMEntriesImages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MOMEntriesImages(String imagesId, String momEntryId, byte[] image) {
		super();
		this.imagesId = imagesId;
		this.momEntryId = momEntryId;
		this.image = image;
	}
	public String getImagesId() {
		return imagesId;
	}
	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}
	public String getMomEntryId() {
		return momEntryId;
	}
	public void setMomEntryId(String momEntryId) {
		this.momEntryId = momEntryId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}

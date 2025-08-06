package com.project.entity;


import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Entity
public class MOMEntries {
	
	 @Id
	 private String momEntryId;
	 private String momId;
	 private String workOrderNo;
	 private String tooleName;
	 private String observation;
	 private String details;
	 private String correctedPoints;
	 private String responsibleAndTarget;
	 @Transient
	 private List<String> illustrationImages;
	 @Transient
	 private List<String> correctedImages;
	 
	 
	 
	 
	 public MOMEntries() {
		super();
		// TODO Auto-generated constructor stub
	}





	 public MOMEntries(String momEntryId, String momId, String workOrderNo, String tooleName, String observation,
			String details, String correctedPoints, String responsibleAndTarget, List<String> illustrationImages,
			List<String> correctedImages) {
		super();
		this.momEntryId = momEntryId;
		this.momId = momId;
		this.workOrderNo = workOrderNo;
		this.tooleName = tooleName;
		this.observation = observation;
		this.details = details;
		this.correctedPoints = correctedPoints;
		this.responsibleAndTarget = responsibleAndTarget;
		this.illustrationImages = illustrationImages;
		this.correctedImages = correctedImages;
	}





	 public String getMomEntryId() {
		return momEntryId;
	}





	 public void setMomEntryId(String momEntryId) {
		 this.momEntryId = momEntryId;
	 }





	 public String getMomId() {
		 return momId;
	 }





	 public void setMomId(String momId) {
		 this.momId = momId;
	 }





	 public String getWorkOrderNo() {
		 return workOrderNo;
	 }





	 public void setWorkOrderNo(String workOrderNo) {
		 this.workOrderNo = workOrderNo;
	 }





	 public String getTooleName() {
		 return tooleName;
	 }





	 public void setTooleName(String tooleName) {
		 this.tooleName = tooleName;
	 }





	 public String getObservation() {
		 return observation;
	 }





	 public void setObservation(String observation) {
		 this.observation = observation;
	 }





	 public String getDetails() {
		 return details;
	 }





	 public void setDetails(String details) {
		 this.details = details;
	 }





	 public String getCorrectedPoints() {
		 return correctedPoints;
	 }





	 public void setCorrectedPoints(String correctedPoints) {
		 this.correctedPoints = correctedPoints;
	 }





	 public String getResponsibleAndTarget() {
		 return responsibleAndTarget;
	 }





	 public void setResponsibleAndTarget(String responsibleAndTarget) {
		 this.responsibleAndTarget = responsibleAndTarget;
	 }





	 public List<String> getIllustrationImages() {
		 return illustrationImages;
	 }





	 public void setIllustrationImages(List<String> illustrationImages) {
		 this.illustrationImages = illustrationImages;
	 }





	 public List<String> getCorrectedImages() {
		 return correctedImages;
	 }





	 public void setCorrectedImages(List<String> correctedImages) {
		 this.correctedImages = correctedImages;
	 }





	 @PrePersist
	    public void prePersist() {
	        if (momEntryId == null) {
	        	momEntryId = UuidCreator.getTimeOrderedEpoch().toString();  // UUIDv7-style sortable UUID
	        }
	    }

}

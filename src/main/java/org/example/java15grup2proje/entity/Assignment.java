package org.example.java15grup2proje.entity;

import org.example.java15grup2proje.entity.enums.EAssetStatus;

public class Assignment {
	Long assignmentDate;
	String assetName;
	String assetDescription;
	Long assignedToPersonnelId;
	Long assignedToManagerId;
	Long responseDate;
	EAssetStatus status;
	String rejectionReason;
}
package com.rescuefly.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="uploaded_documents")
public class UploadedDocument {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String filename;
 @Column(nullable=false) private String documentType="TRAVEL_DOCUMENT";
 @Column(nullable=false) private String status="PARSED_DEMO";
 @Column(length=3000) private String extractedData;
 @Column(nullable=false) private double confidence=0.94;
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @ManyToOne(fetch=FetchType.LAZY,optional=false) private User owner;
 public UploadedDocument(){}
 public Long getId(){return id;} public String getFilename(){return filename;} public void setFilename(String v){filename=v;}
 public String getDocumentType(){return documentType;} public void setDocumentType(String v){documentType=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getExtractedData(){return extractedData;} public void setExtractedData(String v){extractedData=v;} public double getConfidence(){return confidence;} public void setConfidence(double v){confidence=v;}
 public Instant getCreatedAt(){return createdAt;} public User getOwner(){return owner;} public void setOwner(User v){owner=v;}
}

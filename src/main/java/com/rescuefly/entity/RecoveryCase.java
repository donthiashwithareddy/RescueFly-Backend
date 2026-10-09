package com.rescuefly.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="recovery_cases")
public class RecoveryCase {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String caseType;
 @Column(nullable=false) private String title;
 @Column(nullable=false) private String status="OPEN";
 @Column(length=4000) private String details;
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @ManyToOne(fetch=FetchType.LAZY, optional=false) private User owner;
 public RecoveryCase(){}
 public Long getId(){return id;} public String getCaseType(){return caseType;} public void setCaseType(String v){caseType=v;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;} public String getDetails(){return details;} public void setDetails(String v){details=v;} public Instant getCreatedAt(){return createdAt;} public User getOwner(){return owner;} public void setOwner(User v){owner=v;}
}

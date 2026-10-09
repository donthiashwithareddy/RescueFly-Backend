package com.rescuefly.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="trips")
public class Trip {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String referenceCode;
 @Column(nullable=false) private String passengerName;
 @Column(nullable=false) private String airline;
 @Column(nullable=false) private String flightNumber;
 @Column(nullable=false) private String origin;
 @Column(nullable=false) private String destination;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private TripStatus status=TripStatus.MONITORING;
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @Column(length=2000) private String disruptionNote;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) private User owner;
 public Trip(){}
 public Long getId(){return id;} public String getReferenceCode(){return referenceCode;} public void setReferenceCode(String v){referenceCode=v;}
 public String getPassengerName(){return passengerName;} public void setPassengerName(String v){passengerName=v;}
 public String getAirline(){return airline;} public void setAirline(String v){airline=v;} public String getFlightNumber(){return flightNumber;} public void setFlightNumber(String v){flightNumber=v;}
 public String getOrigin(){return origin;} public void setOrigin(String v){origin=v;} public String getDestination(){return destination;} public void setDestination(String v){destination=v;}
 public TripStatus getStatus(){return status;} public void setStatus(TripStatus v){status=v;} public Instant getCreatedAt(){return createdAt;}
 public String getDisruptionNote(){return disruptionNote;} public void setDisruptionNote(String v){disruptionNote=v;} public User getOwner(){return owner;} public void setOwner(User v){owner=v;}
}

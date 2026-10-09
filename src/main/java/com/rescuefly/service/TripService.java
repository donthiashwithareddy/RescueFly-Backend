package com.rescuefly.service;
import com.rescuefly.dto.TripDtos.*; import com.rescuefly.entity.*; import com.rescuefly.repository.*; import org.springframework.stereotype.Service; import java.util.*; import java.util.UUID;
@Service public class TripService{
 private final TripRepository trips; private final UserRepository users; public TripService(TripRepository t,UserRepository u){trips=t;users=u;}
 public TripResponse create(String email,CreateTripRequest r){User u=user(email);Trip t=new Trip();t.setReferenceCode("RF-"+UUID.randomUUID().toString().substring(0,8).toUpperCase());t.setPassengerName(r.passengerName());t.setAirline(r.airline());t.setFlightNumber(r.flightNumber());t.setOrigin(r.origin());t.setDestination(r.destination());t.setDisruptionNote(r.disruptionNote());t.setOwner(u);return dto(trips.save(t));}
 public List<TripResponse> list(String email){return trips.findByOwnerOrderByCreatedAtDesc(user(email)).stream().map(this::dto).toList();}
 public Map<String,Object> stats(String email){User u=user(email);return Map.of("total",trips.countByOwner(u),"monitoring",trips.countByOwnerAndStatus(u,TripStatus.MONITORING),"disrupted",trips.countByOwnerAndStatus(u,TripStatus.DISRUPTED),"resolved",trips.countByOwnerAndStatus(u,TripStatus.RESOLVED));}
 private User user(String e){return users.findByEmailIgnoreCase(e).orElseThrow(()->new IllegalArgumentException("User not found"));}
 private TripResponse dto(Trip t){return new TripResponse(t.getId(),t.getReferenceCode(),t.getPassengerName(),t.getAirline(),t.getFlightNumber(),t.getOrigin(),t.getDestination(),t.getStatus().name(),t.getDisruptionNote(),t.getCreatedAt().toString());}
}

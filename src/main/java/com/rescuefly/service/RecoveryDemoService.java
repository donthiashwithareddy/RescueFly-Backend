package com.rescuefly.service;
import com.rescuefly.entity.*; import com.rescuefly.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class RecoveryDemoService{
 private final TripRepository trips; private final UserRepository users; private final CaseRepositoryAdapter cases;
 public RecoveryDemoService(TripRepository t,UserRepository u,CaseRepositoryAdapter c){trips=t;users=u;cases=c;}
 public Map<String,Object> simulate(String email,Long id){var t=trip(email,id);t.setStatus(TripStatus.DISRUPTED);t.setDisruptionNote("SIMULATED CANCELLATION — demo mode. External airline status is not queried.");trips.save(t);return plan(t);}
 public Map<String,Object> plan(String email,Long id){return plan(trip(email,id));}
 private Map<String,Object> plan(Trip t){
  List<Map<String,Object>> flights=List.of(
   Map.of("airline","SkyJet Airways","flight","SJ 204","departure","12:40","arrival","14:55","price","₹8,450","score",92,"tag","Best balance"),
   Map.of("airline","IndiGo","flight","6E 421","departure","15:20","arrival","17:35","price","₹7,980","score",88,"tag","Lowest fare"),
   Map.of("airline","Air India","flight","AI 518","departure","18:10","arrival","20:25","price","₹9,240","score",85,"tag","Flexible"));
  String waiver="Subject: Request for cancellation-related date shift / waiver\n\nHello Hotel Partner,\n\nMy flight "+t.getFlightNumber()+" was disrupted. Please consider a one-time date shift or cancellation waiver for booking connected to trip "+t.getReferenceCode()+". Cancellation evidence can be attached.\n\nRequested outcome: preserve the booking value and move the stay to the revised travel date.\n\nRegards,\nRescueFly traveler";
  return Map.of("tripId",t.getId(),"referenceCode",t.getReferenceCode(),"status",t.getStatus().name(),"mode","DEMO / SANDBOX ADAPTERS","alternativeFlights",flights,"hotelWaiver",waiver,"compensation",Map.of("jurisdictions",List.of("DGCA India","EU261","US DOT"),"assessment","Potential eligibility — evidence and exact itinerary must be verified before filing","claimReady",true),"steps",List.of("Cancellation detected (simulated)","Alternative flights ranked", "Vendor waiver drafted", "Compensation rules screened", "Recovery plan ready for review"));
 }
 private Trip trip(String e,Long id){var u=users.findByEmailIgnoreCase(e).orElseThrow();return trips.findById(id).filter(x->x.getOwner().getId().equals(u.getId())).orElseThrow(()->new IllegalArgumentException("Trip not found"));}
}

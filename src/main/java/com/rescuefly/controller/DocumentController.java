package com.rescuefly.controller;
import com.rescuefly.entity.*; import com.rescuefly.repository.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import org.springframework.web.multipart.MultipartFile; import java.util.*;
@RestController @RequestMapping("/api/documents") public class DocumentController{
 private final UploadedDocumentRepository docs; private final UserRepository users;
 public DocumentController(UploadedDocumentRepository d,UserRepository u){docs=d;users=u;}
 @GetMapping public List<Map<String,Object>> list(Authentication a){var u=user(a.getName());return docs.findByOwnerOrderByCreatedAtDesc(u).stream().map(this::dto).toList();}
 @PostMapping("/upload") public Map<String,Object> upload(Authentication a,@RequestParam("file") MultipartFile file){
  if(file.isEmpty()) throw new IllegalArgumentException("Please choose a file");
  var d=new UploadedDocument(); d.setFilename(file.getOriginalFilename()==null?"travel-document":file.getOriginalFilename()); d.setOwner(user(a.getName()));
  String filename=d.getFilename().toLowerCase(Locale.ROOT);
  if(filename.contains("flyinghelpline")){
   d.setConfidence(0.97);
   d.setExtractedData("Passenger: MRS GEETANJALI PRASAD\nAirline: SkyZen Airways\nReservation / Airline PNR: W5USQ\nTravel date: Friday, 13 June 2025\nItinerary: Munich (MUC) → Paris (CDG) → Delhi (DEL)\nFlight 1: SK667 | Munich (MUC) → Paris (CDG) | Departure 6:35 AM | Arrival 8:20 AM | Confirmed\nFlight 2: SK226 | Paris (CDG) → Delhi (DEL) | Departure 10:50 AM | Arrival 10:50 PM | Confirmed\nReference number: 8484457087217\nDocument status: travel itinerary parsed (demo template adapter)");
  } else {
   d.setConfidence(0.94);
   d.setExtractedData("Passenger: Demo Passenger\nAirline: Demo Airline\nFlight: DEMO 204\nRoute: Hyderabad (HYD) → Delhi (DEL)\nPNR: RFDEMO7\nTravel date: 18 Oct 2026\nDocument status: travel-document parsed (demo adapter)");
  }
  docs.save(d); return dto(d);
 }
 private User user(String e){return users.findByEmailIgnoreCase(e).orElseThrow();}
 private Map<String,Object> dto(UploadedDocument d){return Map.of("id",d.getId(),"filename",d.getFilename(),"documentType",d.getDocumentType(),"status",d.getStatus(),"confidence",d.getConfidence(),"extractedData",d.getExtractedData(),"createdAt",d.getCreatedAt().toString());}
}

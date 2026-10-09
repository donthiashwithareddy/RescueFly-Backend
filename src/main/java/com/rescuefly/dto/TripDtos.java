package com.rescuefly.dto;
import jakarta.validation.constraints.*;
public final class TripDtos {
 private TripDtos(){}
 public record CreateTripRequest(@NotBlank String passengerName,@NotBlank String airline,@NotBlank String flightNumber,@NotBlank String origin,@NotBlank String destination,@Size(max=2000) String disruptionNote){}
 public record TripResponse(Long id,String referenceCode,String passengerName,String airline,String flightNumber,String origin,String destination,String status,String disruptionNote,String createdAt){}
}

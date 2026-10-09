package com.rescuefly.dto;
import jakarta.validation.constraints.*;
public final class CaseDtos {
 private CaseDtos(){}
 public record CreateCaseRequest(@NotBlank String caseType,@NotBlank String title,@Size(max=4000) String details){}
 public record CaseResponse(Long id,String caseType,String title,String status,String details,String createdAt){}
}

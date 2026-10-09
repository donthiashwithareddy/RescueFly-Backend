package com.rescuefly.service;
import com.rescuefly.dto.CaseDtos.*; import com.rescuefly.entity.*; import com.rescuefly.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class CaseService{
 private final RecoveryCaseRepository cases; private final UserRepository users; public CaseService(RecoveryCaseRepository c,UserRepository u){cases=c;users=u;}
 public CaseResponse create(String email,CreateCaseRequest r){User u=user(email);RecoveryCase c=new RecoveryCase();c.setCaseType(r.caseType());c.setTitle(r.title());c.setDetails(r.details());c.setOwner(u);return dto(cases.save(c));}
 public List<CaseResponse> list(String email){return cases.findByOwnerOrderByCreatedAtDesc(user(email)).stream().map(this::dto).toList();}
 private User user(String e){return users.findByEmailIgnoreCase(e).orElseThrow(()->new IllegalArgumentException("User not found"));}
 private CaseResponse dto(RecoveryCase c){return new CaseResponse(c.getId(),c.getCaseType(),c.getTitle(),c.getStatus(),c.getDetails(),c.getCreatedAt().toString());}
}

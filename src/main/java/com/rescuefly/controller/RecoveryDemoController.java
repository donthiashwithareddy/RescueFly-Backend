package com.rescuefly.controller;
import com.rescuefly.service.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/recovery") public class RecoveryDemoController{
 private final RecoveryDemoService s; private final CaseRepositoryAdapter cases; public RecoveryDemoController(RecoveryDemoService x,CaseRepositoryAdapter c){s=x;cases=c;}
 @PostMapping("/simulate-cancellation/{tripId}") public Map<String,Object> simulate(Authentication a,@PathVariable Long tripId){return s.simulate(a.getName(),tripId);}
 @GetMapping("/plan/{tripId}") public Map<String,Object> plan(Authentication a,@PathVariable Long tripId){return s.plan(a.getName(),tripId);}
 @PostMapping("/create-cases/{tripId}") public Map<String,Object> createCases(Authentication a,@PathVariable Long tripId){var p=s.plan(a.getName(),tripId);cases.create(a.getName(),"VENDOR_WAIVER","Hotel waiver request — "+p.get("referenceCode"),(String)p.get("hotelWaiver"));cases.create(a.getName(),"COMPENSATION","Compensation assessment — "+p.get("referenceCode"),"Potential eligibility screened for DGCA India / EU261 / US DOT. Evidence review required.");return Map.of("message","Recovery cases created","referenceCode",p.get("referenceCode"));}
}

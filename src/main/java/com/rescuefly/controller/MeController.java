package com.rescuefly.controller;
import com.rescuefly.dto.AuthDtos.UserResponse; import com.rescuefly.repository.UserRepository; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/me") public class MeController{private final UserRepository users;public MeController(UserRepository u){users=u;}@GetMapping public UserResponse me(Authentication a){var u=users.findByEmailIgnoreCase(a.getName()).orElseThrow();return new UserResponse(u.getId(),u.getName(),u.getEmail(),u.getRole().name());}}

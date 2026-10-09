package com.rescuefly.service;
import com.rescuefly.dto.AuthDtos.*; import com.rescuefly.entity.*; import com.rescuefly.repository.UserRepository; import com.rescuefly.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class AuthService{
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 @Transactional public AuthResponse register(RegisterRequest r){if(users.existsByEmailIgnoreCase(r.email())) throw new IllegalArgumentException("Email already registered"); User u=new User();u.setName(r.name().trim());u.setEmail(r.email().trim().toLowerCase());u.setPasswordHash(encoder.encode(r.password()));u.setRole(Role.USER);users.save(u);return new AuthResponse(jwt.generate(u.getEmail(),u.getRole().name()),toDto(u));}
 public AuthResponse login(LoginRequest r){User u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));if(!encoder.matches(r.password(),u.getPasswordHash()))throw new IllegalArgumentException("Invalid email or password");return new AuthResponse(jwt.generate(u.getEmail(),u.getRole().name()),toDto(u));}
 public UserResponse toDto(User u){return new UserResponse(u.getId(),u.getName(),u.getEmail(),u.getRole().name());}
}

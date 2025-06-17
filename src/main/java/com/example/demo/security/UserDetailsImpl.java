package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

 User user;
 
 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  var authorities = new ArrayList<GrantedAuthority>();
  for (Role  role : user.getRoles()) {
   authorities.add(new SimpleGrantedAuthority(role.getTitre()));
  }
  return authorities;
 }

 @Override
 public String getPassword() {
  // TODO Auto-generated method stub
  return user.getPassword();
 }

 @Override
 public String getUsername() {
  // TODO Auto-generated method stub
  return user.getUsername();
 }

}
 
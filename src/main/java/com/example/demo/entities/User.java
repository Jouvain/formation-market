package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 Integer id;
 @NonNull
 String firstname;
 @NonNull
 String lastname;
 @NonNull
 String username;
 @NonNull
 String password;
 @NonNull
 @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
 List<Role> roles;
 @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
 List<Formation> formations;
}
 
package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entities.Formation;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.FormationRepository;
import com.example.demo.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class SpringBootFormationMarketApplication implements ApplicationRunner{
//  @Autowired
//     private FormationRepository formationRepository;
//  @Autowired
//  private UserRepository userRepository;
//  @Autowired
//  private PasswordEncoder encoder;

    private final FormationRepository formationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public SpringBootFormationMarketApplication(FormationRepository formationRepository,
                                                UserRepository userRepository,
                                                PasswordEncoder encoder) {
        this.formationRepository = formationRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

 public static void main(String[] args) {
  SpringApplication.run(SpringBootFormationMarketApplication.class, args);
 }

 @Override
 public void run(ApplicationArguments args) throws Exception {
  formationRepository.saveAll(
     List.of(
      new Formation("Web Services", "Aixon", 4L, LocalDate.of(2025, 9, 2)),
      new Formation("Java Introduction", "Aix", 5L, LocalDate.of(2025, 7, 1)),
      new Formation("Python Introduction", "Roubaix", 3L, LocalDate.of(2026, 7, 8)),
      new Formation("Javascript et prototypes", "Aix-en-Provence", 3L, LocalDate.of(2023, 7, 8))
     )
  );
  userRepository.saveAll( 
        List.of( 
          new User("Bob", "BOBBIE", "user", encoder.encode("user"), List.of(new Role("USER"))),
          new User("Superbob", "SUPERBOBBIE", "admin", encoder.encode("admin"), List.of(new Role("ADMIN")))
        )  
     );
  
 }

}
 
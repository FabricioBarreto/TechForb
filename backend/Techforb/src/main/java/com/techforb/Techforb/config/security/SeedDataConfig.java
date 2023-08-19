package com.techforb.Techforb.config.security;


import com.techforb.Techforb.models.Role;
import com.techforb.Techforb.models.User;
import com.techforb.Techforb.repository.UserRepository;
import com.techforb.Techforb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .fullname("Fabricio Barreto")
                    .email("fabrib40@gmail.com")
                    .password(passwordEncoder.encode("springbootapplication"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            userService.createUser(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}


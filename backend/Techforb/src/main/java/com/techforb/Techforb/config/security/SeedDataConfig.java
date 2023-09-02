package com.techforb.Techforb.config.security;


import com.techforb.Techforb.models.Menu;
import com.techforb.Techforb.models.Role;
import com.techforb.Techforb.models.TypeDocumentEnum;
import com.techforb.Techforb.models.User;
import com.techforb.Techforb.repository.MenuRepository;
import com.techforb.Techforb.repository.UserRepository;
import com.techforb.Techforb.service.CardService;
import com.techforb.Techforb.service.MenuService;
import com.techforb.Techforb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MenuRepository menuRepository;

    @Override
    public void run(String... args) throws Exception {


        if (userRepository.count() == 0) {
            User admin = User
                    .builder()
                    .fullname("Fabricio Barreto")
                    .email("fabrib40@gmail.com")
                    .password(passwordEncoder.encode("springbootapplication"))
                    .numberDocument("43023002")
                    .typeDocument(TypeDocumentEnum.DNI)
                    .role(Role.ROLE_ADMIN)
                    .build();
            userService.createUser(admin);
        }

        if (menuRepository.count() == 0){
            List<Menu> menus = new ArrayList<>();
            menus.add(new Menu(1,"home","/"));
            menus.add(new Menu(2,"cards","/cards"));
            menus.add(new Menu(3,"transaction","/transaction"));
            menus.add(new Menu(4,"logout","/logout"));
            menuRepository.saveAll(menus);
        }
    }
}


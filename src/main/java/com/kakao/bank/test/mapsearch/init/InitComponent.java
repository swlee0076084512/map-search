package com.kakao.bank.test.mapsearch.init;

import com.kakao.bank.test.mapsearch.domain.user.model.User;
import com.kakao.bank.test.mapsearch.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitComponent implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        User user = new User("test", passwordEncoder.encode("1234"),"USER");
        userRepository.save(user);

        User admin = new User("admin", passwordEncoder.encode("1234"), "ADMIN");
        userRepository.save(admin);
    }
}
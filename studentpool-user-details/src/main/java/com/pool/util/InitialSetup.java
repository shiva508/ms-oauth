package com.pool.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pool.modal.UserEntity;
import com.pool.repository.UsersRepository;

@Component
public class InitialSetup {
	@Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                "qswe3mg84mfjtu",
                "Shiva",
                "Dasari",
                "dasarishiva1@gmail.com",
                bCryptPasswordEncoder.encode("Dasari508201"),
                "",
                false);

        usersRepository.save(user);
    }
}

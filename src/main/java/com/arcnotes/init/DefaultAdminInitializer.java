package com.arcnotes.init;

import com.arcnotes.entity.User;
import com.arcnotes.security.AppRole;
import com.arcnotes.service.UserService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DefaultAdminInitializer implements CommandLineRunner {

    private final DefaultAdminProperties adminProps;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (adminProps.getCreateDefault()) {
            log.info("Trying to create a default admin \"{}\"", adminProps.getNickname());
            Try
                    .of(() -> {
                        Option<User> existingAdmin = userService.findByNickname(adminProps.getNickname());

                        if (existingAdmin.isDefined()) {
                            throw new RuntimeException("Default admin is already exists");
                        }

                        User defaultAdmin = new User(
                                adminProps.getNickname(),
                                "Admin",
                                passwordEncoder.encode(adminProps.getPassword()),
                                AppRole.ADMIN.getGrantedAuthorities(),
                                true
                        );

                        return userService.save(defaultAdmin);
                    })
                    .onFailure(t -> log.warn("Failed to create a default admin. Reason: {}", t.getMessage()))
                    .onSuccess(defAdmin ->
                            log.info("Successfully created a default admin \"{}\"", defAdmin.getNickname())
                    );
        }
    }
}
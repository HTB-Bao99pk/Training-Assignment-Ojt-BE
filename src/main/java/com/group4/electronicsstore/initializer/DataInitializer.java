        package com.group4.electronicsstore.initializer;

        import java.util.stream.IntStream;
        import java.util.Optional;

        import org.springframework.boot.CommandLineRunner;
        import org.springframework.context.annotation.Profile;
        import org.springframework.stereotype.Component;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.lang.Nullable;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

        import com.group4.electronicsstore.entity.User;
        import com.group4.electronicsstore.entity.Role;
        import com.group4.electronicsstore.repository.UserRepository;

        @Component
        @Profile("!test")
        public class DataInitializer implements CommandLineRunner {

            private final UserRepository userRepository;
            private final PasswordEncoder passwordEncoder;

            @Autowired
            public DataInitializer(UserRepository userRepository, @Nullable PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder != null ? passwordEncoder : new BCryptPasswordEncoder();
            }

            @Override
            public void run(String... args) throws Exception {
                // create admin if missing
                Optional<User> adminOpt = userRepository.findByUsername("admin");
                if (adminOpt.isEmpty()) {
                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setPassword(passwordEncoder.encode("Admin@123"));
                    admin.setEmail("admin@example.com");
                    admin.setFullName("Administrator");
                    admin.setRole(Role.ADMIN); // use enum constant, not new Role(...)

                    userRepository.save(admin);
                }

                // create 5 regular users user1..user5
                IntStream.rangeClosed(1, 5).forEach(i -> {
                    String uname = "user" + i;
                    if (userRepository.findByUsername(uname).isEmpty()) {
                        User u = new User();
                        u.setUsername(uname);
                        u.setPassword(passwordEncoder.encode(uname + "@123"));
                        u.setEmail(uname + "@example.com");
                        u.setFullName("User " + i);
                        u.setRole(Role.USER); // use enum constant

                        userRepository.save(u);
                    }
                });
            }
        }
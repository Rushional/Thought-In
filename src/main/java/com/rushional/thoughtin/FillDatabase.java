package com.rushional.thoughtin;

import com.rushional.thoughtin.entities.Entry;
import com.rushional.thoughtin.entities.User;
import com.rushional.thoughtin.repositories.EntryRepository;
import com.rushional.thoughtin.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

//    This is a temporary measure.
//    I add date to the database so that I have stuff to show
//    I've also temporarily switched ddl-auto in properties from create to create-drop, so
//    TODO: don't forget to change the properties back!

@Configuration
public class FillDatabase {
    private static final Logger log = LoggerFactory.getLogger(FillDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EntryRepository entryRepository, UserRepository userRepository) {
        User user = new User(1, "Rushional");
        Entry e1 = new Entry(1, user, new Timestamp((System.currentTimeMillis())));
        Entry e2 = new Entry(2, user, new Timestamp((System.currentTimeMillis())));
        return args -> {
            log.info("Preloading " + userRepository.save(user));
            log.info("Preloading " + entryRepository.save(e1));
            log.info("Preloading " + entryRepository.save(e2));
        };
    }
}

package com.rushional.thoughtin.repositories;

import com.rushional.thoughtin.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}

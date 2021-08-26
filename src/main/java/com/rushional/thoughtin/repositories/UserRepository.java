package com.rushional.thoughtin.repositories;

import com.rushional.thoughtin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}

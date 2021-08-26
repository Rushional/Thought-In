package com.rushional.thoughtin.repositories;

import com.rushional.thoughtin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

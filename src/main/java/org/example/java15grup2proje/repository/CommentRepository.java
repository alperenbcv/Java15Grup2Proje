package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, String> {
	Optional<Comment> findByManagerIdAndState(String id, Integer state);
	
	List<Comment> findAllByState(Integer state);
}
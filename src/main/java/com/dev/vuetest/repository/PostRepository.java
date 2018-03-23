package com.dev.vuetest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.vuetest.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAll();

	List<Post> findByCreatorId(Long id);

}

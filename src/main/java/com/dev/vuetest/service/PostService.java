package com.dev.vuetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.vuetest.entity.Post;
import com.dev.vuetest.entity.User;
import com.dev.vuetest.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	public void insert(Post post) {
		postRepository.save(post);
	}

	public List<Post> findByUser(User user) {
		return postRepository.findByCreatorId(user.getId());
	}

}

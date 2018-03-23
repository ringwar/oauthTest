package com.dev.vuetest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.vuetest.config.CustomUserDetails;
import com.dev.vuetest.entity.Post;
import com.dev.vuetest.service.PostService;
import com.dev.vuetest.service.UserService;

@RestController
public class MainController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

////	@GetMapping(value="/")
//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}

	@GetMapping(value="/posts")
	public List<Post> posts() {
		return postService.getAllPosts();
	}

	@PostMapping(value="/post")
	public String publishPost(@RequestBody Post post) {
		CustomUserDetails userDetails =
				(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(post.getDateCreated() == null)
			post.setDateCreated(new Date());

		post.setCreator(userService.getUser(userDetails.getUsername()));
		postService.insert(post);

		return "Post was published";
	}

	@GetMapping(value = "/posts/{username}")
	public List<Post> postByUserName(@PathVariable String username) {
		return postService.findByUser(userService.getUser(username));
	}
}

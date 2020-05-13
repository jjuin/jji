package com.example.spring.api;

import reactor.core.publisher.Mono;

public interface BlogUseCase {

	Mono<ResponseNaverBlog> findBlogByQuery(String query);
}

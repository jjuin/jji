//package com.example.spring.api;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import reactor.core.publisher.Mono;
//
//@RestController
////@RequestMapping("/api")
//public class ApiController {
//	 private final BlogUseCase blogUseCase;
//	 
//	 public ApiController(BlogUseCase blogUseCase) {
//	        this.blogUseCase = blogUseCase;
//	   }
//	 
//	@GetMapping("/api")
//    public Mono<ResponseNaverBlog> findBlogByQuery(){
//
//        return blogUseCase.findBlogByQuery("스프링부트");
//    }
//}

package com.sud.service1.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/list")
	public List<Request> show() {
		
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		Request r = new Request();
		r.setUserId(1001);
		r.setId(2001);
		r.setTitle("test");
		r.setCompleted(true);
		
		ResponseEntity<List> rs = restTemplate.
				exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, entity, List.class);
				//postForEntity("https://jsonplaceholder.typicode.com/posts",r,String.class, entity);
		
		if(rs.getStatusCode().is2xxSuccessful()) {
			return rs.getBody();
		}
		return null;
	}
	
	@PostMapping("/insert")
	public String insertRecord(@RequestBody Student st) {

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		/*
		 * Student student = new Student(); student.setRollNo(22);
		 * student.setName("Ram"); student.setAddress("Goa");
		 * student.setEmail("ram@yahoo.com"); student.setMobile(987654656);
		 */
		
		ResponseEntity<String> rs = restTemplate.
				postForEntity("http://localhost:8080/student/save", st, String.class, header);
				
				//exchange("http://localhost:8080/student/save", HttpMethod.POST, null, String.class, student);
		
		if(rs.getStatusCode().is2xxSuccessful()) {
			return "Record inserted successfully !!!!";
		}
		
		return null;
	}
}

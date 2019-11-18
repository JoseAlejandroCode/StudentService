package com.microservice.registration.controller;

import com.microservice.registration.model.document.Student;
import com.microservice.registration.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public Mono<ResponseEntity<Flux<Student>>> index(){
    return Mono.just(ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(studentService.findAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Student>> detail(String id){
    return studentService.findById(id).map(student -> ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(student));
  }

  @PostMapping
  public  Mono<ResponseEntity<Student>> save(Student student){
    return studentService.create(student).map(s -> ResponseEntity
            .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }


}

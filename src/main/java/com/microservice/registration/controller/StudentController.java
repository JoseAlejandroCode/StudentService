package com.microservice.registration.controller;

import com.microservice.registration.model.document.Student;
import com.microservice.registration.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public Mono<ResponseEntity<Flux<Student>>> listar(){
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(studentService.findAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Student>> ver(@PathVariable String id){
    return studentService.findById(id).map(student -> ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(student));
  }

  @PostMapping
  public  Mono<ResponseEntity<Student>> registrar(@RequestBody Student student){
    return studentService.create(student).map(s -> ResponseEntity
            .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Student>> actulizar(@RequestBody Student student, @PathVariable String id){
    return studentService.update(student, id).map(s -> ResponseEntity
            .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
    return studentService.delete(id)
            .flatMap(p -> Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

}

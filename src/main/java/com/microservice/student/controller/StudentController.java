package com.microservice.student.controller;

import com.microservice.student.component.StudentConverter;
import com.microservice.student.model.dto.StudentDTO;
import com.microservice.student.service.StudentService;
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
  @Autowired
  private StudentConverter studentConverter;

  @GetMapping
  public Mono<ResponseEntity<Flux<StudentDTO>>> listar(){
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(studentService.findAll().flatMap(
                    student -> Mono.just(studentConverter.converToDto(student))
            )));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<StudentDTO>> ver(@PathVariable String id){
    return studentService.findById(id)
            .flatMap(student -> Mono.just(studentConverter.converToDto(student)))
            .map(student -> ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(student));
  }

  @PostMapping
  public  Mono<ResponseEntity<StudentDTO>> registrar(@RequestBody StudentDTO student){
    return studentService.create(studentConverter.convertToDocument(student))
            .flatMap(s -> Mono.just(studentConverter.converToDto(s)))
            .map(s -> ResponseEntity
            .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<StudentDTO>> actulizar(@RequestBody StudentDTO student, @PathVariable String id){
    return studentService.update(studentConverter.convertToDocument(student), id)
            .flatMap(s -> Mono.just(studentConverter.converToDto(s)))
            .map(s -> ResponseEntity
                .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
    return studentService.delete(id)
            .flatMap(p -> Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

}

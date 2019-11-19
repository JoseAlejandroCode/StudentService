package com.microservice.registration.model.service;

import com.microservice.registration.model.document.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
  Flux<Student> findAll();
  Mono<Student> findById(String id);
  Mono<Student> create(Student student);
  Mono<Student> update(Student student, String id);
  Mono<Void> delete(String id);
}

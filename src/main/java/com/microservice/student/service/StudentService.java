package com.microservice.student.service;

import com.microservice.student.model.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface StudentService {
  Flux<StudentDto> findAll();
  Mono<StudentDto> findById(String id);
  Mono<StudentDto> create(StudentDto student);
  Mono<StudentDto> update(StudentDto student, String id);
  Mono<Void> delete(String id);
  Flux<StudentDto> findByFullNameLikeIgnoreCase(String fullName);
  Mono<StudentDto> findByNumberDocument(String numberDocument);
  Flux<StudentDto> findByBirthdate(Date dateStart, Date dateEnd);
}

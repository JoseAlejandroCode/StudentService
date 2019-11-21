package com.microservice.student.repository;

import com.microservice.student.model.document.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
   Flux<Student> findByFullNameIgnoreCaseLike(String fullName);
   Mono<Student> findByNumberDocument(String numberDocument);
   Flux<Student> findByBirthdateBetween(Date dateStart, Date dateEnd);
}

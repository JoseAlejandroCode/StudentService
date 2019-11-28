package com.microservice.student.repository;

import com.microservice.student.model.document.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import java.util.Date;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
   Flux<Student> findByIdInstitute(String idInstitute);
   Flux<Student> findByFullNameIgnoreCaseLike(String fullName);
   Mono<Student> findByNumberDocument(String numberDocument);
   Flux<Student> findByBirthdateBetween(Date dateStart, Date dateEnd);
}

package com.microservice.registration.model.dao;

import com.microservice.registration.model.document.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentDAO extends ReactiveMongoRepository<Student, String> {
}

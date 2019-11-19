package com.microservice.student.model.dao;

import com.microservice.student.model.document.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentDAO extends ReactiveMongoRepository<Student, String> {
}

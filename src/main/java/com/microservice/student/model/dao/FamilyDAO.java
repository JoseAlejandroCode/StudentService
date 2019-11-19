package com.microservice.student.model.dao;

import com.microservice.student.model.document.Family;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FamilyDAO extends ReactiveMongoRepository<Family, String> {
}

package com.microservice.registration.model.dao;

import com.microservice.registration.model.document.Family;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FamilyDAO extends ReactiveMongoRepository<Family, String> {
}

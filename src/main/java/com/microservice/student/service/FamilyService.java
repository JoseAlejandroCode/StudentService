package com.microservice.student.service;

import com.microservice.student.model.dto.FamilyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyService {
  Mono<FamilyDto> save(FamilyDto familyDto);
  Mono<Void> deleteByStudent(String idStudent);
  Flux<FamilyDto> findByStudent(String idStudent);
}

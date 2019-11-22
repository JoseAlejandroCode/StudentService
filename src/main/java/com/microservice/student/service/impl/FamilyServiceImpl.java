package com.microservice.student.service.impl;

import com.microservice.student.model.dto.FamilyDto;
import com.microservice.student.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import java.util.Collections;
import reactor.core.publisher.Mono;

@Service
public class FamilyServiceImpl implements FamilyService {

  @Autowired
  private WebClient webClient;

  @Override
  public Mono<FamilyDto> save(FamilyDto familyDto) {
    return webClient.post()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(familyDto))
            .retrieve()
            .bodyToMono(FamilyDto.class);
  }

  @Override
  public Mono<FamilyDto> update(FamilyDto familyDto, String id) {
    return webClient.put()
            .uri("/{id}", Collections.singletonMap("id", id))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(familyDto))
            .retrieve()
            .bodyToMono(FamilyDto.class);
  }

  @Override
  public Mono<Void> deleteByStudent(String idStudent) {
    return webClient.delete()
            .uri("/student/{idStudent}", Collections.singletonMap("idStudent", idStudent))
            .retrieve()
            .bodyToMono(Void.class);
  }

  @Override
  public Flux<FamilyDto> findByStudent(String idStudent) {
    return webClient.get()
            .uri("/student/{idStudent}", Collections.singletonMap("idStudent", idStudent))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(FamilyDto.class);
  }

}

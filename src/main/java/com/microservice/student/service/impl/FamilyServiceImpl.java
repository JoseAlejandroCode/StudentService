package com.microservice.student.service.impl;

import com.microservice.student.model.dto.FamilyDto;
import com.microservice.student.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
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
}
package com.microservice.student.service.impl;

import com.microservice.student.component.StudentConverter;
import com.microservice.student.model.dto.StudentDto;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.model.document.Student;
import com.microservice.student.service.FamilyService;
import com.microservice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private FamilyService familyService;

  @Autowired
  private StudentConverter studentConverter;

  @Override
  public Flux<StudentDto> findAll() {
    return studentRepository.findAll()
            .flatMap(student -> {
              StudentDto studentDto = studentConverter.convertToDto(student);
              studentDto.setFamilyList(familyService.findByStudent(student.getId()).collectList().block());
              return Mono.just(studentDto);
            });
  }

  @Override
  public Mono<StudentDto> findById(String id) {
    return studentRepository.findById(id)
            .flatMap(student -> {
              StudentDto studentDto = studentConverter.convertToDto(student);
              studentDto.setFamilyList(familyService.findByStudent(student.getId()).collectList().block());
              return Mono.just(studentDto);
            });
  }

  @Override
  public Mono<StudentDto> create(StudentDto student) {
    return studentRepository.save(studentConverter.convertToDocument(student))
            .flatMap(s -> Mono.just(studentConverter.convertToDto(s)))
            .flatMap(s -> {
              student.getFamilyList().forEach(family -> {
                family.setIdStudent(s.getId());
                 s.addFamily(familyService.save(family).block());
              });
              return Mono.just(s);
            });
  }

  @Override
  public Mono<StudentDto> update(StudentDto student, String id) {
    return findById(id).flatMap(s -> {
      s.setFullName(student.getFullName());
      s.setBirthdate(student.getBirthdate());
      s.setGender(student.getGender());
      s.setTypeDocument(student.getTypeDocument());
      s.setNumberDocument(student.getNumberDocument());
      return studentRepository.save(studentConverter.convertToDocument(s))
              .flatMap(st -> Mono.just(studentConverter.convertToDto(st)));
    });
  }

  @Override
  public Mono<Void> delete(String  id) {
    return findById(id)
            .flatMap(s -> studentRepository.delete(studentConverter.convertToDocument(s)));
  }
}

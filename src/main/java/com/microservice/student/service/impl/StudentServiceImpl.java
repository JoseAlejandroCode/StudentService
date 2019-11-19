package com.microservice.student.service.impl;

import com.microservice.student.component.StudentConverter;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.model.document.Student;
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
  private StudentConverter studentConverter;

  @Override
  public Flux<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }

  @Override
  public Mono<Student> create(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Mono<Student> update(Student student, String id) {
    return findById(id).flatMap(s -> {
      s.setFullName(student.getFullName());
      s.setBirthdate(student.getBirthdate());
      s.setGender(student.getGender());
      s.setTypeDocument(student.getTypeDocument());
      s.setNumberDocument(student.getNumberDocument());
      return studentRepository.save(s);
    });
  }

  @Override
  public Mono<Void> delete(String  id) {
    return findById(id).flatMap(s -> studentRepository.delete(s));
  }
}

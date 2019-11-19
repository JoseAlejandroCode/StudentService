package com.microservice.registration.model.service.impl;

import com.microservice.registration.model.dao.StudentDAO;
import com.microservice.registration.model.document.Student;
import com.microservice.registration.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentDAO studentDAO;

  @Override
  public Flux<Student> findAll() {
    return studentDAO.findAll();
  }

  @Override
  public Mono<Student> findById(String id) {
    return studentDAO.findById(id);
  }

  @Override
  public Mono<Student> create(Student student) {
    return studentDAO.save(student);
  }

  @Override
  public Mono<Student> update(Student student, String id) {
    return findById(id).flatMap(s -> {
      s.setFullName(student.getFullName());
      s.setBirthdate(student.getBirthdate());
      s.setGender(student.getGender());
      s.setTypeDocument(student.getTypeDocument());
      s.setNumberDocument(student.getNumberDocument());
      return studentDAO.save(s);
    });
  }

  @Override
  public Mono<Void> delete(String  id) {
    return findById(id).flatMap(s -> studentDAO.delete(s));
  }
}

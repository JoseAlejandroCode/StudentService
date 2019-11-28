package com.microservice.student.service.impl;

import com.microservice.student.component.StudentConverter;
import com.microservice.student.model.dto.StudentDto;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.service.FamilyService;
import com.microservice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

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
                 family.addPartner(s);
                 s.addFamily(familyService.save(family).block());
              });
              return update(s, s.getId());
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
      student.getFamilyList().forEach(family -> s.addFamily(family));
      student.getCourseList().forEach(course -> s.addCourse(course));
      return studentRepository.save(studentConverter.convertToDocument(s))
              .flatMap(st -> Mono.just(studentConverter.convertToDto(st)))
              .flatMap(st -> {
                student.getFamilyList().forEach(family -> {
                  family.addPartner(st);
                  st.addFamily(familyService.update(family, family.getId()).block());
                });
                return Mono.just(st);
              });
    });
  }

  @Override
  public Mono<Void> delete(String  id) {
    return findById(id)
              .flatMap(s -> studentRepository.delete(studentConverter.convertToDocument(s))
                        .zipWith(familyService.deleteByStudent(s.getId()))
                        .then());
  }

  @Override
  public Flux<StudentDto> findByFullNameLikeIgnoreCase(String fullName) {
    return studentRepository.findByFullNameIgnoreCaseLike(fullName)
            .flatMap(student -> {
              StudentDto studentDto = studentConverter.convertToDto(student);
              studentDto.setFamilyList(familyService.findByStudent(student.getId()).collectList().block());
              return Mono.just(studentDto);
            });
  }

  @Override
  public Mono<StudentDto> findByNumberDocument(String numberDocument) {
    return studentRepository.findByNumberDocument(numberDocument)
            .flatMap(student -> {
              StudentDto studentDto = studentConverter.convertToDto(student);
              studentDto.setFamilyList(familyService.findByStudent(student.getId()).collectList().block());
              return Mono.just(studentDto);
            });
  }

  @Override
  public Flux<StudentDto> findByBirthdate(Date dateStart, Date dateEnd) {
    return studentRepository.findByBirthdateBetween(dateStart, dateEnd)
            .flatMap(student -> {
              StudentDto studentDto = studentConverter.convertToDto(student);
              studentDto.setFamilyList(familyService.findByStudent(student.getId()).collectList().block());
              return Mono.just(studentDto);
            });
  }

  @Override
  public Flux<StudentDto> findByIdInstitute(String idInstitute) {
    return studentRepository.findByIdInstitute(idInstitute)
            .flatMap(student -> Mono.just(studentConverter.convertToDto(student)));
  }
}

package com.microservice.student.component;

import com.microservice.student.model.document.Student;
import com.microservice.student.model.dto.FamilyDto;
import com.microservice.student.model.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

  public StudentDto convertToDto(Student student){
    StudentDto studentDto = new StudentDto();
    studentDto.setId(student.getId());
    studentDto.setFullName(student.getFullName());
    studentDto.setTypeDocument(student.getTypeDocument());
    studentDto.setNumberDocument(student.getNumberDocument());
    studentDto.setGender(student.getGender());
    studentDto.setBirthdate(student.getBirthdate());
    return studentDto;
  }

  public Student convertToDocument(StudentDto studentDTO){
    Student student = new Student();
    student.setId(studentDTO.getId());
    student.setFullName(studentDTO.getFullName());
    student.setTypeDocument(studentDTO.getTypeDocument());
    student.setNumberDocument(studentDTO.getNumberDocument());
    student.setGender(studentDTO.getGender());
    student.setBirthdate(studentDTO.getBirthdate());
    studentDTO.getFamilyList().forEach(f -> student.addFamily(f.getId()));
    studentDTO.getCourseList().forEach(c -> student.addCourse(c.getId()));
    return student;
  }

}

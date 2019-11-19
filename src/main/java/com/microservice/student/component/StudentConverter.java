package com.microservice.student.component;

import com.microservice.student.model.document.Student;
import com.microservice.student.model.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
  public StudentDto converToDto(Student student){
    StudentDto studentDTO = new StudentDto();

    return studentDTO;
  }

  public Student convertToDocument(StudentDto studentDTO){
    Student student = new Student();

    return student;
  }

}

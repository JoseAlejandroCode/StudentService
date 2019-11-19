package com.microservice.student.component;

import com.microservice.student.model.document.Student;
import com.microservice.student.model.dto.StudentDTO;
import com.sun.corba.se.impl.javax.rmi.CORBA.StubDelegateImpl;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
  public StudentDTO converToDto(Student student){
    StudentDTO studentDTO = new StudentDTO();

    return studentDTO;
  }

  public Student convertToDocument(StudentDTO studentDTO){
    Student student = new Student();

    return student;
  }

}

package com.microservice.student.controller;

import com.microservice.student.component.StudentConverter;
import com.microservice.student.model.document.Student;
import com.microservice.student.model.dto.StudentDto;
import com.microservice.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(value="students", description="Operations pertaining to students")
@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private StudentService studentService;
  @Autowired
  private StudentConverter studentConverter;

  @ApiOperation(value = "View a list of available students", response = StudentDto.class)
  @GetMapping
  public Mono<ResponseEntity<Flux<StudentDto>>> findAll(){
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(studentService.findAll()));
  }

  @ApiOperation(value = "View a student by ID", response = StudentDto.class)
  @GetMapping("/{id}")
  public Mono<ResponseEntity<StudentDto>> finById(@PathVariable String id){
    return studentService.findById(id)
            .map(student -> ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(student));
  }

  @ApiOperation(value = "Save a student", response = StudentDto.class)
  @PostMapping
  public  Mono<ResponseEntity<StudentDto>> save(@Valid @RequestBody StudentDto student){
    return studentService.create(student)
            .flatMap(s -> Mono.just(s))
            .map(s -> ResponseEntity
            .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @ApiOperation(value = "Update a student", response = StudentDto.class)
  @PutMapping("/{id}")
  public Mono<ResponseEntity<StudentDto>> update(@RequestBody StudentDto student, @PathVariable String id){
    return studentService.update(student, id)
            .flatMap(s -> Mono.just(s))
            .map(s -> ResponseEntity
                .created(URI.create("/api/students")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @ApiOperation(value = "Delete of available student", response = Mono.class)
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
    return studentService.delete(id)
            .flatMap(p -> Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
  }

  @ApiOperation(value = "View a student by name", response = StudentDto.class)
  @GetMapping("/name/{fullName}")
  public Mono<ResponseEntity<Flux<StudentDto>>> findByFullName(@PathVariable String fullName){
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON)
            .body(studentService.findByFullNameLikeIgnoreCase(fullName.toUpperCase())));
  }

  @ApiOperation(value = "View a student by number document", response = StudentDto.class)
  @GetMapping("/document/{numberDocument}")
  public Mono<ResponseEntity<StudentDto>> findByNumberDocument(@PathVariable String numberDocument){
    return studentService.findByNumberDocument(numberDocument)
            .map(student -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(student));
  }

  @ApiOperation(value = "View a student by birth date between two dates", response = StudentDto.class)
  @GetMapping("/birthdate/{dateStart}/{dateEnd}")
  public Mono<ResponseEntity<Flux<StudentDto>>> findByBirthdate(@PathVariable String dateStart
  , @PathVariable String dateEnd) throws ParseException {
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON)
            .body(studentService.findByBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd))));
  }
}

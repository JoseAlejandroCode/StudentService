package com.microservice.student;

import com.microservice.student.model.dto.StudentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class StudentApplicationTests {

	@Autowired
	private WebTestClient testClient;

	private StudentDto studentDto  = new StudentDto();

	@Test
	public void saveTest(){
		studentDto.setFullName("Student Test");
		studentDto.setTypeDocument("DNI");
		studentDto.setNumberDocument("11111111");
		studentDto.setGender(true);
		studentDto.setBirthdate(new Date());

		testClient.post()
						.uri("/api/students")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.body(Mono.just(studentDto), StudentDto.class)
						.exchange()
						.expectStatus().isCreated()
						.expectHeader().contentType(MediaType.APPLICATION_JSON)
						.expectBody(StudentDto.class)
						.consumeWith(response -> {
								studentDto = response.getResponseBody();
								Assertions.assertThat(studentDto.getId()).isNotEmpty();
								Assertions.assertThat(studentDto.getFullName()).isNotEmpty();
								Assertions.assertThat(studentDto.getTypeDocument()).isNotEmpty();
								Assertions.assertThat(studentDto.getNumberDocument()).isNotEmpty();
								Assertions.assertThat(studentDto.getGender()).isNotNull();
								Assertions.assertThat(studentDto.getBirthdate()).isNotNull();
						});
	}

	@Test
	public void getAllTest() {
		testClient.get()
						.uri("api/students")
						.accept(MediaType.APPLICATION_JSON)
						.exchange()
						.expectStatus().isOk()
						.expectHeader().contentType(MediaType.APPLICATION_JSON)
						.expectBodyList(StudentDto.class)
						.consumeWith(response -> {
							List<StudentDto> students = response.getResponseBody();
							students.forEach(student -> System.out.println(student.getFullName()));
						});
	}

	@Test
	public void getByIdTest(){
		testClient.get()
						.uri("/{id}", Collections.singletonMap("id", studentDto.getId()))
						.accept(MediaType.APPLICATION_JSON)
						.exchange()
						.expectStatus().isOk()
						.expectHeader().contentType(MediaType.APPLICATION_JSON)
						.expectBody(StudentDto.class)
						.consumeWith(response -> {
								StudentDto student = response.getResponseBody();
								Assertions.assertThat(student.getId()).isNotEmpty();
								Assertions.assertThat(student.getFullName()).isNotEmpty();
								Assertions.assertThat(student.getTypeDocument()).isNotEmpty();
								Assertions.assertThat(student.getNumberDocument()).isNotEmpty();
								Assertions.assertThat(student.getGender()).isNotNull();
								Assertions.assertThat(student.getBirthdate()).isNotNull();
							});
	}

}

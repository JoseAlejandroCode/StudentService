package com.microservice.registration.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "students")
public class Student {
  @Id
  private String id;
  private String fullName;
  private String typeDocument;
  private String numberDocument;
  private Boolean gender;
  private Date birthdate;
  private List<Family> family;

  public Student() {
    family = new ArrayList<>();
  }

  public Student(String fullName, String typeDocument, String numberDocument, Boolean gender, Date birthdate) {
    this();
    this.fullName = fullName;
    this.typeDocument = typeDocument;
    this.numberDocument = numberDocument;
    this.gender = gender;
    this.birthdate = birthdate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getTypeDocument() {
    return typeDocument;
  }

  public void setTypeDocument(String typeDocument) {
    this.typeDocument = typeDocument;
  }

  public String getNumberDocument() {
    return numberDocument;
  }

  public void setNumberDocument(String numberDocument) {
    this.numberDocument = numberDocument;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public List<Family> getFamily() {
    return family;
  }

  public void addFamily(Family family) {
    this.family.add(family);
  }
}

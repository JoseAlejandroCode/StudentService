package com.microservice.student.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

abstract class PersonDto implements Serializable {
  private String id;
  @NotEmpty
  private String fullName;
  @NotEmpty
  private String typeDocument;
  @NotEmpty
  private String numberDocument;
  @NotNull
  private Boolean gender;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;

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
}

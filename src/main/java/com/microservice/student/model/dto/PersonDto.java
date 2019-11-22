package com.microservice.student.model.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

abstract class PersonDto implements Serializable {
  private String id;
  @ApiModelProperty(value = "Fullname of student", required = true)
  @NotEmpty
  private String fullName;
  @ApiModelProperty(value = "Type document of student", required = true)
  @NotEmpty
  private String typeDocument;
  @ApiModelProperty(value = "Number document of student", required = true)
  @NotEmpty
  private String numberDocument;
  @ApiModelProperty(value = "Gender of student", required = true)
  @NotNull
  private Boolean gender;
  @ApiModelProperty(value = "Birthdate of student", required = true)
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

package com.microservice.student.model.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;

abstract class PersonDto implements Serializable {
  private String id;

  @ApiModelProperty(value = "Fullname", required = true)
  @NotEmpty(message = "Name is required field")
  private String fullName;

  @ApiModelProperty(value = "Type document", required = true)
  @NotEmpty(message = "Type document is required field")
  private String typeDocument;

  @ApiModelProperty(value = "Number document", required = true)
  @NotEmpty(message = "Number document is required field")
  private String numberDocument;

  @ApiModelProperty(value = "Gender", required = true)
  @NotNull(message = "Gender is required field")
  private Boolean gender;

  @ApiModelProperty(value = "Birthdate", required = true)
  @NotNull(message = "Birthdate is required field")
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

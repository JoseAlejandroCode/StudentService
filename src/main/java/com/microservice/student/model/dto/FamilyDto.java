package com.microservice.student.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

public class FamilyDto extends PersonDto {

  @ApiModelProperty(value = "Relationship of family", required = true)
  @NotEmpty
  private String relationship;
  @ApiModelProperty(value = "Student id of family", required = false)
  private String idStudent;

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public String getIdStudent() {
    return idStudent;
  }

  public void setIdStudent(String idStudent) {
    this.idStudent = idStudent;
  }
}

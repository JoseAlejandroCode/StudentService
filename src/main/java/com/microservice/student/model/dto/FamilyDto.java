package com.microservice.student.model.dto;

public class FamilyDto extends PersonDto {

  private String relationship;
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

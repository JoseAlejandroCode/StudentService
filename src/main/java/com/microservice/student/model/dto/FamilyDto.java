package com.microservice.student.model.dto;

public class FamilyDto extends PersonDto {

  private RelationshipDto relationship;
  private String idStudent;


  public String getIdStudent() {
    return idStudent;
  }

  public void setIdStudent(String idStudent) {
    this.idStudent = idStudent;
  }

  public RelationshipDto getRelationship() {
    return relationship;
  }

  public void setRelationship(RelationshipDto relationship) {
    this.relationship = relationship;
  }
}

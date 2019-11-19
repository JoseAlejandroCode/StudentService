package com.microservice.student.model.dto;

import java.util.Date;

public class FamilyDTO extends PersonDTO {

  private String relationship;

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }
}

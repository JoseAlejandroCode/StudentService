package com.microservice.student.model.dto;

import java.util.List;

public class StudentDto extends PersonDto {

  private List<FamilyDto> familyList;

  public List<FamilyDto> getFamilyList() {
    return familyList;
  }

  public void setFamilyList(List<FamilyDto> familyList) {
    this.familyList = familyList;
  }
}

package com.microservice.student.model.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class StudentDto extends PersonDto {

  @ApiModelProperty(value = "List family of student", required = false)
  private List<FamilyDto> familyList;

  public StudentDto() {
    super();
    familyList = new ArrayList<>();
  }

  public void setFamilyList(List<FamilyDto> familyList) {
    this.familyList = familyList;
  }

  public List<FamilyDto> getFamilyList() {
    return familyList;
  }

  public void addFamily(FamilyDto family) {
    this.familyList.add(family);
  }
}

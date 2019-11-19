package com.microservice.student.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StudentDTO extends PersonDTO{

  private List<FamilyDTO> familyList;

  public List<FamilyDTO> getFamilyList() {
    return familyList;
  }

  public void setFamilyList(List<FamilyDTO> familyList) {
    this.familyList = familyList;
  }
}

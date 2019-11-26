package com.microservice.student.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class FamilyDto extends PersonDto {

  private List<StudentDto> partnerList;
  @NotEmpty
  private String relationship;

  public FamilyDto() {
    super();
    partnerList = new ArrayList<>();
  }

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public List<StudentDto> getPartnerList() {
    return partnerList;
  }

  public void setPartnerList(List<StudentDto> partnerList) {
    this.partnerList = partnerList;
  }

  public void addPartner(StudentDto partner) {
    this.partnerList.add(partner);
  }
}

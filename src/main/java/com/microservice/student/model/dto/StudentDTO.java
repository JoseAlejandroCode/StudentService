package com.microservice.student.model.dto;

import com.microservice.student.model.document.Family;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StudentDTO implements Serializable {
  private String id;
  private String fullName;
  private String typeDocument;
  private String numberDocument;
  private Boolean gender;
  private Date birthdate;
  private List<Family> familyList;

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

  public List<Family> getFamilyList() {
    return familyList;
  }

  public void setFamilyList(List<Family> familyList) {
    this.familyList = familyList;
  }
}

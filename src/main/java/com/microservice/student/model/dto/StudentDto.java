package com.microservice.student.model.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class StudentDto extends PersonDto {

  @ApiModelProperty(value = "List family of student", required = false)
  private List<FamilyDto> familyList;

  private List<CourseDto> courseList;

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

  public List<CourseDto> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<CourseDto> courseList) {
    this.courseList = courseList;
  }

  public void addCourse(CourseDto course) {
    this.courseList.add(course);
  }
}

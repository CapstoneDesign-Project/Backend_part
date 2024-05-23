package com.example.una.userInfo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateRequest {
    private String newSchool;
    private int newGrade;
    private int newClass;
}

package com.example.una.userInfo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentDTO {
    private Long parentId;
    private String parentEmail;
    private String parentName;
    private String parentPhoneNumber;
    private Long parentKakaoId;
    private String childName;
    private String childSchool;
    private int childGrade;
    private int childClass;
    private int childNumber;
}
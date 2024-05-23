package com.example.una.userInfo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParentDTO {
    private String parentEmail;
    private String parentName;
    private String parentPhoneNumber;
    private Long parentKakaoId;
    private List<ChildDTO> children;
}

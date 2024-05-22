package com.example.una.userInfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher {

    @Id
    @Column(name = "teacher_kakao_id")
    private Long teacherKakaoId;

    @Column(name = "teacher_school")
    private String teacherSchool;

    @Column(name = "teacher_grade")
    private int teacherGrade;

    @Column(name = "teacher_class")
    private int teacherClass;

    @Column(name = "teacher_email")
    private String teacherEmail;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_phone_number")
    private String teacherPhoneNumber;
}

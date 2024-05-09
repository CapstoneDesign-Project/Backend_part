package com.example.una.userInfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teachertest")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

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

    @Column(name = "teacher_kakao_id")
    private Long teacherKakaoId;
}
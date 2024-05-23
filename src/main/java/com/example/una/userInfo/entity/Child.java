package com.example.una.userInfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "child")
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @Column(name = "child_name")
    private String childName;

    @Column(name = "child_school")
    private String childSchool;

    @Column(name = "child_grade")
    private int childGrade;

    @Column(name = "child_class")
    private int childClass;

    @Column(name = "child_number")
    private int childNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_kakao_id")
    private Parent parent;
}

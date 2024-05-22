package com.example.una.userInfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parent")
@Getter
@Setter
public class Parent {

    @Id
    @Column(name = "parent_kakao_id")
    private Long parentKakaoId;

    @Column(name = "parent_email")
    private String parentEmail;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_phone_number")
    private String parentPhoneNumber;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();

    public void addChild(Child child){
        children.add(child);
        child.setParent(this);
    }
}

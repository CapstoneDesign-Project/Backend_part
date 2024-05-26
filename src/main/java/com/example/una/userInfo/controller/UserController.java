package com.example.una.userInfo.controller;

import com.example.una.userInfo.dto.*;
import com.example.una.userInfo.entity.Parent;
import com.example.una.userInfo.entity.Teacher;
import com.example.una.userInfo.service.ParentService;
import com.example.una.userInfo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ParentService parentService;

    @PostMapping("/save-teacher")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody TeacherDTO teacherDTO){
        Teacher savedTeacher = teacherService.saveTeacher(teacherDTO);
        return ResponseEntity.ok(savedTeacher);
    }

    @PostMapping("/save-parent")
    public ResponseEntity<ParentDTO> saveParent(@RequestBody ParentDTO parentDTO){
        ParentDTO savedParentDTO = parentService.saveParent(parentDTO);
        return ResponseEntity.ok(savedParentDTO);
    }

    @PutMapping("/update-teacher/{teacherKakaoId}")
    private ResponseEntity<String> updateTeacher(
            @PathVariable Long teacherKakaoId,
            @RequestBody TeacherUpdateRequest request
    ){
        try {
            teacherService.updateTeacher(teacherKakaoId, request.getNewSchool(), request.getNewGrade(), request.getNewClass());
            return ResponseEntity.ok("Teacher updated successfully.");
        } catch (Exception e) {
            log.error("Error updating teacher with Kakao ID {}", teacherKakaoId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating teacher.");
        }
    }

    @PutMapping("/update-child/{parentKakaoId}/{childName}")
    public ResponseEntity<String> updateChild(
            @PathVariable Long parentKakaoId,
            @PathVariable String childName,
            @RequestBody ChildDTO childDTO
    ){
        try {
            parentService.updateChild(parentKakaoId, childName, childDTO);
            return ResponseEntity.ok("Child with name " + childName + " under parent with Kakao ID " + parentKakaoId + " updated successfully.");
        } catch (Exception e) {
            log.error("Error updating child with name {} under parent with Kakao ID {}", childName, parentKakaoId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating child.");
        }
    }

    @PostMapping("/parents-info")
    public ResponseEntity<List<Map<String, String>>> getParentsInfoByClass(@RequestBody ClassRequestDTO classRequest) {
        String school = classRequest.getSchool();
        int grade = classRequest.getGrade();
        int clazz = classRequest.getClazz();

        List<Parent> parents = parentService.getParentsByChildSchoolGradeAndClass(school, grade, clazz);

        List<Map<String, String>> parentInfoList = parents.stream().map(parent -> {
            Map<String, String> info = new HashMap<>();
            info.put("parentName", parent.getParentName());
            info.put("parentPhoneNumber", parent.getParentPhoneNumber());
            return info;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(parentInfoList);
    }
}

package com.example.una.userInfo.controller;

import com.example.una.userInfo.dto.ChildDTO;
import com.example.una.userInfo.dto.ParentDTO;
import com.example.una.userInfo.dto.TeacherDTO;
import com.example.una.userInfo.dto.TeacherUpdateRequest;
import com.example.una.userInfo.service.ParentService;
import com.example.una.userInfo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ParentService parentService;

    @PostMapping("/save-teacher")
    private void saveTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherService.saveTeacher(teacherDTO);
    }

    @PostMapping("/save-parent")
    private void saveParent(@RequestBody ParentDTO parentDTO){
        parentService.saveParent(parentDTO);
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

    @GetMapping("/parent-info/{parentKakaoId}")
    public ResponseEntity<Map<String, String>> getParentInfo(@PathVariable Long parentKakaoId) {
        Map<String, String> parentInfo = parentService.getParentInfoByKakaoId(parentKakaoId);
        if (parentInfo != null) {
            return ResponseEntity.ok(parentInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

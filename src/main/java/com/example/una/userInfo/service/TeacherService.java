package com.example.una.userInfo.service;

import com.example.una.userInfo.dto.TeacherDTO;
import com.example.una.userInfo.entity.Teacher;
import com.example.una.userInfo.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher saveTeacher(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();

        teacher.setTeacherSchool(teacherDTO.getTeacherSchool());
        teacher.setTeacherGrade(teacherDTO.getTeacherGrade());
        teacher.setTeacherClass(teacherDTO.getTeacherClass());
        teacher.setTeacherEmail(teacherDTO.getTeacherEmail());
        teacher.setTeacherName(teacherDTO.getTeacherName());
        teacher.setTeacherPhoneNumber(teacherDTO.getTeacherPhoneNumber());
        teacher.setTeacherKakaoId(teacherDTO.getTeacherKakaoId());

        return teacherRepository.save(teacher);
    }

    public void updateTeacher(Long teacherKakaoId, String newSchool, int newGrade, int newClass) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherKakaoId);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherSchool(newSchool);
            teacher.setTeacherGrade(newGrade);
            teacher.setTeacherClass(newClass);
            teacherRepository.save(teacher);
            log.info("Teacher with Kakao ID {} updated successfully.", teacherKakaoId);
        } else {
            log.error("Teacher with Kakao ID {} not found.", teacherKakaoId);
        }
    }
}

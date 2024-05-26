package com.example.una.userInfo.repository;

import com.example.una.userInfo.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByParentKakaoId(Long parentKakaoId);

    @Query("SELECT p FROM Parent p JOIN p.children c WHERE c.childSchool = :school AND c.childGrade = :grade AND c.childClass = :clazz")
    List<Parent> findParentsByChildSchoolGradeAndClass(String school, int grade, int clazz);
}

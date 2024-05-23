package com.example.una.userInfo.repository;

import com.example.una.userInfo.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByParentKakaoId(Long parentKakaoId);
}

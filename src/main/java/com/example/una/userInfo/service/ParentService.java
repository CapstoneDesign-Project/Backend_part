package com.example.una.userInfo.service;

import com.example.una.userInfo.dto.ChildDTO;
import com.example.una.userInfo.dto.ParentDTO;
import com.example.una.userInfo.entity.Child;
import com.example.una.userInfo.entity.Parent;
import com.example.una.userInfo.repository.ChildRepository;
import com.example.una.userInfo.repository.ParentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    public ParentDTO saveParent(ParentDTO request) {
        // 부모 정보 저장
        Parent parent = new Parent();
        parent.setParentName(request.getParentName());
        parent.setParentEmail(request.getParentEmail());
        parent.setParentPhoneNumber(request.getParentPhoneNumber());
        parent.setParentKakaoId(request.getParentKakaoId());

        // 자식 정보 저장
        List<ChildDTO> childDTOList = request.getChildren();
        List<Child> children = new ArrayList<>();
        for (ChildDTO childDTO : childDTOList) {
            Child child = new Child();
            child.setChildName(childDTO.getChildName());
            child.setChildSchool(childDTO.getChildSchool());
            child.setChildGrade(childDTO.getChildGrade());
            child.setChildClass(childDTO.getChildClass());
            child.setChildNumber(childDTO.getChildNumber());
            child.setParent(parent);
            children.add(child);
        }
        parent.setChildren(children);

        Parent savedParent = parentRepository.save(parent);

        // Parent를 ParentDTO로 변환
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setParentName(savedParent.getParentName());
        parentDTO.setParentEmail(savedParent.getParentEmail());
        parentDTO.setParentPhoneNumber(savedParent.getParentPhoneNumber());
        parentDTO.setParentKakaoId(savedParent.getParentKakaoId());

        List<ChildDTO> savedChildDTOList = savedParent.getChildren().stream().map(child -> {
            ChildDTO childDTO = new ChildDTO();
            childDTO.setChildId(child.getChildId());
            childDTO.setChildName(child.getChildName());
            childDTO.setChildSchool(child.getChildSchool());
            childDTO.setChildGrade(child.getChildGrade());
            childDTO.setChildClass(child.getChildClass());
            childDTO.setChildNumber(child.getChildNumber());
            return childDTO;
        }).collect(Collectors.toList());

        parentDTO.setChildren(savedChildDTOList);

        return parentDTO;
    }

    public void updateChild(Long parentKakaoId, String childName, ChildDTO childDTO){
        Optional<Parent> optionalParent = parentRepository.findByParentKakaoId(parentKakaoId);
        if(optionalParent.isPresent()){
            Parent parent = optionalParent.get();
            // 부모의 자녀 목록에서 해당하는 자녀 찾기
            Optional<Child> optionalChild = parent.getChildren().stream()
                    .filter(child -> child.getChildName().equals(childName))
                    .findFirst();

            if(optionalChild.isPresent()){
                Child child = optionalChild.get();
                // ChildDTO에서 수정할 자녀 정보 추출하여 업데이트
                child.setChildSchool(childDTO.getChildSchool());
                child.setChildGrade(childDTO.getChildGrade());
                child.setChildClass(childDTO.getChildClass());
                child.setChildNumber(childDTO.getChildNumber());
                child.setChildName(childDTO.getChildName());

                childRepository.save(child);
                log.info("Child with name {} under parent with Kakao ID {} updated successfully.", childName, parentKakaoId);
            } else {
                log.error("Child with name {} not found under parent with Kakao ID {}.", childName, parentKakaoId);
                // 혹은 예외를 던지거나 다른 처리 방법을 선택할 수 있습니다.
            }
        } else{
            log.error("Parent with Kakao ID {} not found", parentKakaoId);
            // 혹은 예외를 던지거나 다른 처리 방법을 선택할 수 있습니다.
        }
    }

    //연락처 조회(부모 이름, 부모 전화번호)
    public Map<String, String> getParentInfoByKakaoId(Long parentKakaoId) {
        Optional<Parent> optionalParent = parentRepository.findByParentKakaoId(parentKakaoId);
        Map<String, String> parentInfo = new HashMap<>();
        if (optionalParent.isPresent()) {
            Parent parent = optionalParent.get();
            parentInfo.put("parentName", parent.getParentName());
            parentInfo.put("parentPhoneNumber", parent.getParentPhoneNumber());
            return parentInfo;
        } else {
            log.error("Parent with Kakao ID {} not found.", parentKakaoId);
            // 혹은 예외를 던지거나 다른 처리 방법을 선택할 수 있습니다.
            return null;
        }
    }

}

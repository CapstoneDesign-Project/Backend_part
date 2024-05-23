package com.example.una.kakaoLogin.controller;

import com.example.una.kakaoLogin.dto.KakaoDTO;
import com.example.una.kakaoLogin.entity.MsgEntity;
import com.example.una.kakaoLogin.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception{
        // 카카오에서 받아온 인가 코드를 사용하여 사용자 정보를 가져옴
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        log.info(request.getParameter("code"));
        // 사용자 정보를 ResponseEntity로 반환
        return ResponseEntity.ok().body(new MsgEntity("Success", kakaoInfo));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam("access_token") String accessToken) {
        try {
            // KakaoService를 사용하여 로그아웃 요청을 보냄
            kakaoService.kakaoLogout(accessToken);

            // 성공적으로 로그아웃했음을 클라이언트에게 응답
            return ResponseEntity.ok("로그아웃 성공");
        } catch (Exception e) {
            // 로그아웃 중에 예외가 발생한 경우, 클라이언트에게 실패를 알림
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 실패: " + e.getMessage());
        }
    }
}


package com.example.una.kakaoLogin.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {
    private long id;
    private String email;
    private String name;
    private String gender;
    private String birthyear;
    private String phone_number;
//    private String shipping_address;
    private String access_token;
    private String refresh_token;
}

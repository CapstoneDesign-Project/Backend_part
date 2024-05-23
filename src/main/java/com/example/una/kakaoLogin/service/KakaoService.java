package com.example.una.kakaoLogin.service;

import com.example.una.kakaoLogin.dto.KakaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoService {

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("인가코드 없음");

        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type"   , "authorization_code");
            params.add("client_id"    , KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code"         , code);
            params.add("redirect_uri" , KAKAO_REDIRECT_URL);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken  = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");
        } catch (Exception e) {
            throw new Exception("API call failed {}", e);
        }

        return getUserInfoWithToken(accessToken, refreshToken);
    }


    private KakaoDTO getUserInfoWithToken(String accessToken, String refreshToken) throws Exception {
        // 토큰 검증 및 재발급
        accessToken = validateAndRefreshToken(accessToken, refreshToken);

        //사용자 정보 가져오기
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj    = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        long id = (long) jsonObj.get("id");
        String email = String.valueOf(account.get("email"));
        String name = String.valueOf(account.get("name"));
        String gender = String.valueOf(account.get("gender"));
        String birthyear = String.valueOf(account.get("birthyear"));
        String phone_number = String.valueOf(account.get("phone_number"));
        String shipping_address = String.valueOf(account.get("shipping_address"));

        return KakaoDTO.builder()
                .id(id)
                .email(email)
                .birthyear(birthyear)
                .gender(gender)
                .name(name)
                .phone_number(phone_number)
//                .shipping_address(shipping_address)
                .access_token(accessToken)
                .refresh_token(refreshToken)
                .build();
    }

    public String validateAndRefreshToken(String accessToken, String refreshToken) throws Exception {
        try {
            // 토큰 검증을 위해 카카오 API 호출
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);

            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_API_URI + "/v1/user/access_token_info",
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            // 토큰이 유효하면 그대로 반환
            if (response.getStatusCode() == HttpStatus.OK) {
                return accessToken;
            } else {
                // 만료되었을 경우 토큰 재발급
                return refreshToken(refreshToken);
            }
        } catch (Exception e) {
            throw new Exception("Failed to validate and refresh token");
        }
    }

    public String refreshToken(String refreshToken) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "refresh_token");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("refresh_token", refreshToken);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            return (String) jsonObj.get("access_token");
        } catch (Exception e) {
            throw new Exception("Failed to refresh token");
        }
    }

    private final static String KAKAO_LOGOUT_URI = "https://kapi.kakao.com";

    public void kakaoLogout(String accessToken) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);

            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_LOGOUT_URI + "/v1/user/logout",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new Exception("Failed to logout from Kakao");
            }
        } catch (Exception e) {
            throw new Exception("Failed to logout from Kakao");
        }
    }
}
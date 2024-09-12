package com.example.mini.please_mini_pjt.hosapi.ctrl;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class HosApiController {

    @Value("${openApi.serviceKey}")
    private String serviceKey;
    
    @Value("${openApi.callBackUrl}")
    private String callBackUrl;
    
    @Value("${openApi.dataType}")
    private String dataType;

    @GetMapping("/Hosinfo")
    public ResponseEntity<String> getMethodName(@RequestParam(value = "Q0") String Q0,
                                                @RequestParam(value = "Q1") String Q1,
                                                @RequestParam(value = "QT") String QT) {
        System.out.println("client end point : /api/Hosinfo");
        System.out.println("serviceKey = " + serviceKey);
        System.out.println("callBackUrl = " + callBackUrl);
        System.out.println("params = " + Q0 + "\t" + Q1 + "\t" + QT);

        try {
            // 한글 파라미터 인코딩 처리
            String encodedQ0 = URLEncoder.encode(Q0, StandardCharsets.UTF_8.toString());
            String encodedQ1 = URLEncoder.encode(Q1, StandardCharsets.UTF_8.toString());
            String encodedQT = URLEncoder.encode(QT, StandardCharsets.UTF_8.toString());

            // 이미 인코딩된 serviceKey를 다시 인코딩하지 않음
            String requestURL = callBackUrl +
                                "?serviceKey=" + serviceKey +
                                "&Q0=" + encodedQ0 +
                                "&Q1=" + encodedQ1 +
                                "&QT=" + encodedQT;

            System.out.println("url check = " + requestURL);

            // URI 클래스를 사용하여 URL 인코딩
            URI uri = new URI(requestURL);

            // RestTemplate을 이용하여 GET 요청
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

            System.out.println("API response = " + response);

            // 요청 결과를 그대로 반환
            return response;

        } catch (URISyntaxException e) {
            System.out.println("URI 생성 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(400).body("URI 생성 중 오류 발생");
        } catch (Exception e) {
            System.out.println("API 요청 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(500).body("API 요청 중 오류 발생");
        }
    }
}

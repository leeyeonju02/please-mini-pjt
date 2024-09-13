package com.example.mini.please_mini_pjt.hosapi.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mini.please_mini_pjt.hosapi.domain.HosDetailDTO;
import com.example.mini.please_mini_pjt.hosapi.domain.HosItemDTO;
import com.example.mini.please_mini_pjt.hosapi.service.HosApiService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class HosApiController {
    @Autowired
    private HosApiService hosApiService;


    @Value("${openApi.serviceKey}")
    private String serviceKey;
    
    @Value("${openApi.callBackUrl}")
    private String callBackUrl;
    
    @Value("${openApi.dataType}")
    private String dataType;

    @Value("${openApi.callBackUrl2}")
    private String callBackUrl2;

    @GetMapping("/Hosinfo")
    public ResponseEntity<List<HosItemDTO>> getMethodName(@RequestParam(value = "Q0", required = false) String Q0,
                                                @RequestParam(value = "Q1", required = false) String Q1,
                                                @RequestParam(value = "QT", required = false) String QT) {
        System.out.println("client end point : /api/Hosinfo");
        System.out.println("serviceKey = " + serviceKey);
        System.out.println("callBackUrl = " + callBackUrl);
        System.out.println("params = " + Q0 + "\t" + Q1 + "\t" + QT);

        HttpURLConnection http = null;
        InputStream stream = null;
        String result = null; 
        List<HosItemDTO> list = null;

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

            

            //수정본 
            URL url = new URL(requestURL);
            http = (HttpURLConnection)url.openConnection();
            System.out.println("http connection = " + http);
            int code = http.getResponseCode();
            System.out.println("http response code = " + code);
            if(code == 200) {
                stream = http.getInputStream();
                result = readString(stream);
                System.out.println("result = " + result);
                list = hosApiService.parseXml(result);
                
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return new ResponseEntity<>(list, HttpStatus.OK);
       
    }
    
    @GetMapping("/Hosdetail")
    public ResponseEntity<List<HosDetailDTO>> getDetail(@RequestParam(value = "hpid", required = false) String hpid) {
        System.out.println("client end point : /api/Hosdetail");
        System.out.println("serviceKey = " + serviceKey);
        System.out.println("callBackurl = " + callBackUrl2); 
        System.out.println("params hpid = " + hpid);

        HttpURLConnection http = null;
        InputStream stream = null;
        String result = null; 
        List<HosDetailDTO> list = null;

        try {
            // 한글 파라미터 인코딩 처리
            //String encodedQ0 = URLEncoder.encode(hpid, StandardCharsets.UTF_8.toString());

            // 이미 인코딩된 serviceKey를 다시 인코딩하지 않음
            String requestURL = callBackUrl2 +
                                "?serviceKey=" + serviceKey +
                                "&HPID=" + hpid;
                     

            System.out.println("url check = " + requestURL);


            //수정본 
            URL url = new URL(requestURL);
            http = (HttpURLConnection)url.openConnection();
            System.out.println("http connection = " + http);
            int code = http.getResponseCode();
            System.out.println("http response code = " + code);
            if(code == 200) {
                stream = http.getInputStream();
                result = readString(stream);
                System.out.println("result = " + result);
                list = hosApiService.parseXml2(result);
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }
        return new ResponseEntity<>(list, HttpStatus.OK);


    }

    public String readString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
        String input = null ;
        StringBuilder result = new StringBuilder();
        while((input = br.readLine()) != null) {
            result.append(input + "\n\r");
        }
        br.close();
        return result.toString();
    }
}
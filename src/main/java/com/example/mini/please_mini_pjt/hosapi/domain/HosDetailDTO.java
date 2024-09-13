package com.example.mini.please_mini_pjt.hosapi.domain;

import lombok.Data;

@Data
public class HosDetailDTO {
    private String dutyName; //기관명
    private String dutyTime1s; 
    private String dutyTime1c;
    private String dutyMapping; //간이약도
    private String dutyTel3; //응급실전화
    private String wgs84Lon; //경도
    private String wgs84Lat; //위도
    
}

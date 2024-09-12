package com.example.mini.please_mini_pjt.hosapi.domain;

import lombok.Data;

@Data
public class HosItemDTO {
    private String dutyAddr; //주소
    private String dutyDivName; //병원분류명
    private String dutyName; // 병원관명
    private String dutyTel1; //대표전화 
    private String hpid; //기관 id
    private int rnum; //일련번호

}

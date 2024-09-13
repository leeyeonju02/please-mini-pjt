package com.example.mini.please_mini_pjt.hosinfo.domain;

import lombok.Data;

@Data
public class HosResponseDTO {
    private int id; 
    private String dutyName;
    private String hpid;
    private String dutyAddr;
    private String dutytel;
    private String dutyDivName;
    private int rnum;
}

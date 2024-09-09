package com.example.mini.please_mini_pjt.hosinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.mini.please_mini_pjt.hosinfo.dao.HosMapper;
import com.example.mini.please_mini_pjt.hosinfo.domain.HosRequestsDTO;
import com.example.mini.please_mini_pjt.hosinfo.domain.HosResponseDTO;

@Service
public class HosinfoService {
    @Autowired
    private HosMapper hosMapper;

    public List<HosResponseDTO> findAll() {
        System.out.println("debug >>> service All, " + hosMapper);
        return hosMapper.findAllRow();
    }

    public void create(HosRequestsDTO params) {
        System.out.println("debug >>> service create " + hosMapper);
        hosMapper.insertRow(params);
    }

    public void delete(Map<String, Integer> map ) {
        System.out.println("debug >>> service delete " + hosMapper);
        hosMapper.deleteRow(map);
    }

  

   
    
}

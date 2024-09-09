package com.example.mini.please_mini_pjt.hosinfo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.mini.please_mini_pjt.hosinfo.domain.HosRequestsDTO;
import com.example.mini.please_mini_pjt.hosinfo.domain.HosResponseDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface HosMapper {
    public List<HosResponseDTO> findAllRow();
    
    public void insertRow(HosRequestsDTO params);

    public void deleteRow(Map<String, Integer> map);

}

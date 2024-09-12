package com.example.mini.please_mini_pjt.hosinfo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mini.please_mini_pjt.hosinfo.domain.HosRequestsDTO;
import com.example.mini.please_mini_pjt.hosinfo.domain.HosResponseDTO;
import com.example.mini.please_mini_pjt.hosinfo.service.HosinfoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/hosinfo")
public class HosinfoController {
    @Autowired
    private HosinfoService hosinfoService;

    @GetMapping("/index")
    public ResponseEntity<Object> landing() {
        System.out.println("client endpoint : /hos/index" + hosinfoService);
        List<HosResponseDTO> list = hosinfoService.findAll();
        System.out.println("result size = " + list.size());
        if( list.size() == 0) {
            Map<String, String> map = new HashMap<>();
            map.put("info", "저장된 데이터가 존재하지 않습니다. ");
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody HosRequestsDTO params) {
        System.out.println("client endpoint : /hos/save");
        System.out.println("params : " + params);
        hosinfoService.create(params);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<Void> delete(@PathVariable(name="id") Integer id) {
        System.out.println("debug >>> hos controller client path : /hos/delete");
        System.out.println("debug >>> id params value " + id);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        hosinfoService.delete(map);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    


    
    


    
    
}

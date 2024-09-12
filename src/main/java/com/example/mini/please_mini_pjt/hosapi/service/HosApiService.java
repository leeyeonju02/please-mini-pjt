package com.example.mini.please_mini_pjt.hosapi.service;


import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.mini.please_mini_pjt.hosapi.domain.HosItemDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class HosApiService {

    // 기존에 String을 받던 부분을 Document로 수정
    public List<HosItemDTO> parsingXml(Document document) {
        List<HosItemDTO> hosItemList = new ArrayList<>();

        try {
            // <item> 태그 목록 가져오기
            NodeList itemListNodes = document.getElementsByTagName("item");

            // 각 <item> 태그를 순회하며 데이터 추출
            for (int i = 0; i < itemListNodes.getLength(); i++) {
                Node itemNode = itemListNodes.item(i);
                NodeList childNodes = itemNode.getChildNodes();

                HosItemDTO hosItemDTO = new HosItemDTO();

                // 각 <item> 내의 자식 노드들 순회
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);

                    // 태그 이름에 따라 HosItemDTO에 값 설정
                    switch (childNode.getNodeName()) {
                        case "dutyAddr":
                            hosItemDTO.setDutyAddr(childNode.getTextContent());
                            break;
                        case "dutyDivName":
                            hosItemDTO.setDutyDivName(childNode.getTextContent());
                            break;
                        case "dutyName":
                            hosItemDTO.setDutyName(childNode.getTextContent());
                            break;
                        case "dutyTel1":
                            hosItemDTO.setDutyTel1(childNode.getTextContent());
                            break;
                        case "hpid":
                            hosItemDTO.setHpid(childNode.getTextContent());
                            break;
                        case "rnum":
                            hosItemDTO.setRnum(Integer.parseInt(childNode.getTextContent()));
                            break;
                        default:
                            break;
                    }
                }

                // 리스트에 추가
                hosItemList.add(hosItemDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hosItemList; // 변환된 데이터를 반환
    }
}

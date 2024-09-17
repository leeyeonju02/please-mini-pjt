package com.example.mini.please_mini_pjt.hosapi.service;

import org.springframework.stereotype.Service;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.mini.please_mini_pjt.hosapi.domain.HosDetailDTO;
import com.example.mini.please_mini_pjt.hosapi.domain.HosItemDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class HosApiService {

    // XML 문자열을 파싱하여 HosItemDTO 리스트로 변환
    public List<HosItemDTO> parseXml(String xmlData) {
        List<HosItemDTO> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));

            NodeList items = doc.getElementsByTagName("item");
            
            // 각각의 <item> 노드를 순회하며 DTO로 변환
            for (int i = 0; i < items.getLength(); i++) {
                Node itemNode = items.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;

                    HosItemDTO dto = new HosItemDTO();
                    dto.setDutyAddr(getTagValue("dutyAddr", itemElement));
                    dto.setDutyDivName(getTagValue("dutyDivName", itemElement));
                    dto.setDutyName(getTagValue("dutyName", itemElement));
                    dto.setDutyTel1(getTagValue("dutyTel1", itemElement));
                    dto.setHpid(getTagValue("hpid", itemElement));
                    dto.setRnum(parseIntSafely(getTagValue("rnum", itemElement)));

                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HosDetailDTO> parseXml2(String xmlData) {
        List<HosDetailDTO> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));
    
            NodeList items = doc.getElementsByTagName("item");
    
            // 각각의 <item> 노드를 순회하며 DTO로 변환
            for (int i = 0; i < items.getLength(); i++) {
                Node itemNode = items.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
    
                    HosDetailDTO dto = new HosDetailDTO();
                    dto.setDutyName(getTagValue("dutyName", itemElement));
                    dto.setDutyTime1s(getTagValue("dutyTime1s", itemElement));
                    dto.setDutyTime1c(getTagValue("dutyTime1c", itemElement));
                    dto.setDutyMapping(getTagValue("dutyMapimg", itemElement));
                    dto.setDutyTel3(getTagValue("dutyTel3", itemElement));
                    dto.setWgs84Lon(getTagValue("wgs84Lon", itemElement));
                    dto.setWgs84Lat(getTagValue("wgs84Lat", itemElement));
    
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // XML 노드에서 태그의 값을 추출하는 헬퍼 메소드
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null && node.getFirstChild() != null) {
                return node.getFirstChild().getNodeValue();
            }
        }
        return null; // 태그나 값이 없을 경우 null 반환
    }

    // 안전하게 문자열을 정수로 변환하는 헬퍼 메소드
    private int parseIntSafely(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // 기본값 반환 또는 로그 출력 가능
        }
    }
}

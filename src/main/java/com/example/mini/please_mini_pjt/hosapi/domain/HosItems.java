package com.example.mini.please_mini_pjt.hosapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class HosItems {
    @JsonProperty("item")
    private List<HosItemDTO> items;

    @JsonCreator
    public HosItems(@JsonProperty("response") JsonNode node) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode itemNode = node.findValue("item");
            this.items = Arrays.stream(mapper.treeToValue(itemNode, HosItemDTO[].class)).toList();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

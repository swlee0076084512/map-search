package com.kakao.bank.test.mapsearch.domain.kakao.vo;

import lombok.Data;

import java.util.List;

@Data
public class MapSearchResponse {
    private Meta meta;
    private List<Document> documents;
}

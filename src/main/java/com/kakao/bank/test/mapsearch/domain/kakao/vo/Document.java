package com.kakao.bank.test.mapsearch.domain.kakao.vo;

import lombok.Data;

import static com.kakao.bank.test.mapsearch.domain.MapAPI.MAP_API_URL;

@Data
public class Document {
    private String addressName;
    private String x;
    private String y;
    private String categoryGroupCode;
    private String categoryGroupName;
    private String categoryName;
    private String distance;
    private String id;
    private String placeName;
    private String phone;
    private String placeUrl;
    private String roadAddressName;
    private String mapApi;

    public String getMapApi(){
        return MAP_API_URL+this.id;
    }


}

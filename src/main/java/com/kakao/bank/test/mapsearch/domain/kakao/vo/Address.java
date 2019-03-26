package com.kakao.bank.test.mapsearch.domain.kakao.vo;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
class Address {
    private String addressName;
    @JsonProperty("region_1depth_name")
    private String region1depthName;
    @JsonProperty("region_2depth_name")
    private String region2depthName;
    @JsonProperty("region_3depth_name")
    private String region3depthName;
    private String x;
    private String y;
}

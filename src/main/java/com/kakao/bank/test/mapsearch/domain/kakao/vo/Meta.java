package com.kakao.bank.test.mapsearch.domain.kakao.vo;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class Meta {
    private int totalCount;
    private int pageableCount;
    @JsonProperty(value = "is_end")
    private boolean isEnd;

    public void setIsEnd(boolean isEnd){
        this.isEnd = isEnd;
    }

    public boolean getIsEnd(){
        return this.isEnd;
    }
}

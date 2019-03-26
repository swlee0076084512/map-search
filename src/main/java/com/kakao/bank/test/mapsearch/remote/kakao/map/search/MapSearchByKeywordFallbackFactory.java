package com.kakao.bank.test.mapsearch.remote.kakao.map.search;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.MapSearchResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MapSearchByKeywordFallbackFactory implements FallbackFactory<MapSearchResponse> {
    @Override
    public MapSearchResponse create(Throwable cause) {
        return null;
    }
}

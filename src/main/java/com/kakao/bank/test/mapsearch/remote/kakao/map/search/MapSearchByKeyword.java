package com.kakao.bank.test.mapsearch.remote.kakao.map.search;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.MapSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url="${feign.kakao.url}", fallbackFactory = MapSearchByKeywordFallbackFactory.class)
public interface MapSearchByKeyword {

    @RequestMapping(method = RequestMethod.GET, path = "/local/search/keyword.json")
    MapSearchResponse getMapResultList(@RequestParam("query") String keyword,
                                       @RequestParam("page") int page,
                                       @RequestParam("size") int size);
}

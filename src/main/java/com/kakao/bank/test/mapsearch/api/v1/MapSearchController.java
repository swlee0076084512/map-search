package com.kakao.bank.test.mapsearch.api.v1;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.PageResult;
import com.kakao.bank.test.mapsearch.domain.keyword.domain.Keyword;
import com.kakao.bank.test.mapsearch.domain.keyword.service.KeywordService;
import com.kakao.bank.test.mapsearch.domain.map.service.MapSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/map")
@RequiredArgsConstructor
public class MapSearchController {
    private final MapSearchService mapSearchService;
    private final KeywordService keywordService;

    @GetMapping("/search")
    public PageResult searchByKeyword(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit){

        Assert.isTrue(limit < 16 , "size is more than max");
        return mapSearchService.searchByKeyword(keyword, page, limit);
    }

    @GetMapping("/search/keyword/top10")
    public List<Keyword> getTop10Keyword() {
        return keywordService.getTop10Keyword();
    }
}

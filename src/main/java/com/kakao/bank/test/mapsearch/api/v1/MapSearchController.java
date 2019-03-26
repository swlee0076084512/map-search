package com.kakao.bank.test.mapsearch.api.v1;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.PageResult;
import com.kakao.bank.test.mapsearch.domain.keyword.domain.Keyword;
import com.kakao.bank.test.mapsearch.domain.keyword.service.KeywordService;
import com.kakao.bank.test.mapsearch.domain.map.service.MapSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}",authentication);
        return mapSearchService.searchByKeyword(keyword, page, limit);
    }

    @GetMapping("/search/keyword/top10")
    public List<Keyword> getTop10Keyword() {
        return keywordService.getTop10Keyword();
    }
}

package com.kakao.bank.test.mapsearch.domain.map.service;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.MapSearchResponse;
import com.kakao.bank.test.mapsearch.domain.kakao.vo.PageResult;
import com.kakao.bank.test.mapsearch.domain.keyword.service.KeywordService;
import com.kakao.bank.test.mapsearch.remote.kakao.map.search.MapSearchByKeyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapSearchService {

    private final MapSearchByKeyword mapSearchByKeyword;
    private final KeywordService keywordService;

    public PageResult searchByKeyword(String keyword, int page, int limit) {
        saveInterestKeyword(keyword, page);
        MapSearchResponse mapSearchResponse = mapSearchByKeyword.getMapResultList(keyword, page, limit);
        return new PageResult<>(mapSearchResponse.getDocuments(), mapSearchResponse.getMeta(), page);
    }

    private void saveInterestKeyword(String keyword, int page) {
        if(page == 1)
            keywordService.saveKeyword(keyword);
    }
}

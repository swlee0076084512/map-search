package com.kakao.bank.test.mapsearch.domain.map.search.service;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.Document;
import com.kakao.bank.test.mapsearch.domain.kakao.vo.PageResult;
import com.kakao.bank.test.mapsearch.domain.map.service.MapSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MapSearchServiceTest {
    @Autowired
    private MapSearchService mapSearchService;

    @Test
    public void searchByKeyword() {
        final String keyword = "나이키";
        final int page = 1;
        final int size = 10;
        PageResult<Document> result = mapSearchService.searchByKeyword(keyword, page, size);

        assertThat(result.getCurrentPage()).isEqualTo(page);
        assertThat(result.getElements().size()).isEqualTo(size);

    }
}
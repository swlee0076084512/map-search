package com.kakao.bank.test.mapsearch.domain.keyword.service;

import com.kakao.bank.test.mapsearch.domain.keyword.domain.Keyword;
import com.kakao.bank.test.mapsearch.domain.keyword.repository.KeywordRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordServiceTest {

    @Autowired
    private KeywordService service;
    @Autowired
    private KeywordRepository keywordRepository;
    private List<String> testKeywords;

    @Before
    public void init(){
        testKeywords = Arrays.asList(
                "강남역",
                "삼성역",
                "선릉역",
                "삼성역",
                "테헤란로",
                "부산",
                "판교",
                "부산",
                "부산",
                "판교",
                "신풍역",
                "어린이대공원",
                "에버랜드",
                "롯데월드",
                "롯데타워",
                "롯데월드",
                "롯데",
                "잠실",
                "강남역",
                "레미안",
                "캐슬",
                "합정역",
                "신도림",
                "서울스퀘아",
                "서울역",
                "롯데타워",
                "롯데월드",
                "롯데",
                "잠실",
                "강남역",
                "레미안",
                "롯데월드",
                "롯데",
                "잠실",
                "테헤란로",
                "부산",
                "판교",
                "부산",
                "부산",
                "판교",
                "신풍역",
                "어린이대공원"

        );
    }

    @Test
    public void saveKeyword() {
        testKeywords.forEach(k -> service.saveKeyword(k));

        Keyword result = keywordRepository.findByKeyword("부산");

        assertThat(result.getKeyword()).isEqualTo("부산");
        assertThat(result.getSearchCount()).isEqualTo(3);

    }

    @Test
    public void getTop10Keyword(){
        testKeywords.forEach(k -> service.saveKeyword(k));
        List<Keyword> result = service.getTop10Keyword();
        result.forEach(System.out::println);

    }
}
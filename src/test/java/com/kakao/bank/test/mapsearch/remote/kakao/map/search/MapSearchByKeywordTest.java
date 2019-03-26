package com.kakao.bank.test.mapsearch.remote.kakao.map.search;

import com.kakao.bank.test.mapsearch.domain.kakao.vo.MapSearchResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapSearchByKeywordTest {
    @Autowired
    private MapSearchByKeyword mapSearchByKeyword;

    @Test
    public void searchByKeyword(){
        MapSearchResponse response = mapSearchByKeyword.getMapResultList("삼성역",1, 10);
        assertThat(response).isNotNull();
    }

}
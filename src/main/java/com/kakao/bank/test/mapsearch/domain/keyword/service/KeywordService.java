package com.kakao.bank.test.mapsearch.domain.keyword.service;

import com.kakao.bank.test.mapsearch.domain.keyword.domain.Keyword;
import com.kakao.bank.test.mapsearch.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    @Async
    @Transactional
    public void saveKeyword(String keyword){
        try {
            Keyword result = keywordRepository.findByKeyword(keyword);
            if (result == null)
                keywordRepository.save(new Keyword(keyword));
            else
                result.setSearchCount(result.getSearchCount() + 1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Keyword> getTop10Keyword() {
        List<Keyword> result = keywordRepository.findAll(new Sort(Sort.Direction.DESC, "searchCount"));
        return result.subList(0, Math.min(10, result.size()));
    }
}

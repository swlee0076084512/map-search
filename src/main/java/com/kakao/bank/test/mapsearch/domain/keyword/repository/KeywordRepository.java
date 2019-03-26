package com.kakao.bank.test.mapsearch.domain.keyword.repository;

import com.kakao.bank.test.mapsearch.domain.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(String keyword);
}

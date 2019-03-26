package com.kakao.bank.test.mapsearch.domain.keyword.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "interest_keyword")
@Data
@AllArgsConstructor
@ToString
public class Keyword {
    public Keyword(){}

    public Keyword(String keyword){
        this.keyword = keyword;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String keyword;
    @Builder.Default
    private long searchCount = 1;
}

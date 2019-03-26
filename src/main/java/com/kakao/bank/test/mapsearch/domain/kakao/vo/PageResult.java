package com.kakao.bank.test.mapsearch.domain.kakao.vo;

import java.util.List;

public class PageResult<T> {

    private int currentPage;
    private int pageableCount;
    private long totalCount;
    private boolean isEnd;
    private List<T> elements;

    public PageResult(List<T> elements, Meta meta, int currentPage){
        this.elements = elements;
        this.totalCount = meta.getTotalCount();
        this.isEnd = meta.getIsEnd();
        this.currentPage = currentPage;
        this.pageableCount = meta.getPageableCount();
    }

    public boolean isEnd(){
        return isEnd;
    }

    public boolean hasPrevious() {
        return currentPage > 1 && totalCount > 0;
    }
    public long getTotalCount() {
        return totalCount;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public int getPageableCount() {
        return pageableCount;
    }
    public List<T> getElements() {
        return elements;
    }
}

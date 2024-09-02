package com.pragma.microservice.stock.domain.utils;

public class MetadataResponse {
    private final int currentPage;
    private final Long totalItems;
    private final int totalPages;
    private final int pageSize;

    public MetadataResponse(int currentPage, Long totalItems, int totalPages, int pageSize) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }
}

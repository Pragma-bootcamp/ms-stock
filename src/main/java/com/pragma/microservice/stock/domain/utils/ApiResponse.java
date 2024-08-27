package com.pragma.microservice.stock.domain.utils;

import java.util.HashMap;

public class ApiResponse <T>{
    private T data;
    private Object metadata;

    public ApiResponse(T data) {
        this.data = data;
        this.metadata = new HashMap<>();
    }
    public ApiResponse(T data, Object metadata) {
        this.data = data;
        this.metadata = metadata;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Object getMetadata() {
        return metadata;
    }
    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }
}

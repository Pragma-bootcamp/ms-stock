package com.pragma.microservice.stock.domain.utils;

public class ApiResponseFormat<T>{
    private T data;
    private MetadataResponse metadata;

    public ApiResponseFormat(T data, MetadataResponse metadata) {
        this.data = data;
        this.metadata = metadata;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public MetadataResponse getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataResponse metadata) {
        this.metadata = metadata;
    }
}

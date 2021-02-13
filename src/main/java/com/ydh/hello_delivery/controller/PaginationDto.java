package com.ydh.hello_delivery.controller;

import lombok.Data;
import lombok.Getter;

@Data
public class PaginationDto {
    int page;
    int size;

    public PaginationDto(int page) {
        this.page = page;
        this.size = 5;
    }
}

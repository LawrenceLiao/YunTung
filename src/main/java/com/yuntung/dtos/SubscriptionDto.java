package com.yuntung.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SubscriptionDto {
    private String title;
    private String artist;
    private String email;
    private String year;
    private String imgUrl;
}

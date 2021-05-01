package com.yuntung.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MusicDto {
    private String title;
    private String artist;
    private String year;
    private String imgUrl;
}

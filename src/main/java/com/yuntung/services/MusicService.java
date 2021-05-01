package com.yuntung.services;

import com.yuntung.dtos.MusicDto;
import com.yuntung.entities.Music;
import com.yuntung.repositories.ArtistRepository;
import com.yuntung.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ArtistRepository artistRepository;


    public List<MusicDto> fetchListOfMusic(String title, String artist, String year) {

        List<Music>list = musicRepository.getListByInfo(title, artist, year);
        List<MusicDto> listOfMusic = new ArrayList<>();
        for (Music music : list) {
            String imgUrl = artistRepository.getArtistByName(music.getArtist()).getImgUrl();
            MusicDto dto = MusicDto.builder()
                    .title(music.getTitle())
                    .artist(music.getArtist())
                    .year(music.getYear())
                    .imgUrl(imgUrl)
                    .build();
            listOfMusic.add(dto);
        }
        return listOfMusic;
    }
}
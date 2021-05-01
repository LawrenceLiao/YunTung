package com.yuntung.controllers;

import com.yuntung.dtos.MusicDto;
import com.yuntung.services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/musics")
    public ResponseEntity queryMusic(@RequestParam("title") String title, @RequestParam("artist") String artist, @RequestParam("year") String year){
        List<MusicDto> listOfMusics = musicService.fetchListOfMusic(title, artist, year);
        if(listOfMusics.size() == 0) {
            return new ResponseEntity("No result is retrieved. Please query again", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listOfMusics, HttpStatus.OK);
    }
}

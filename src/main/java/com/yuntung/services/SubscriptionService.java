package com.yuntung.services;

import com.yuntung.dtos.SubscriptionDto;
import com.yuntung.entities.Subscription;
import com.yuntung.repositories.ArtistRepository;
import com.yuntung.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ArtistRepository artistRepository;


    public List<SubscriptionDto> fetchListOfSubscription(String email) {

        List<Subscription> listOfSubscription = subscriptionRepository.findListByEmail(email);

        List<SubscriptionDto> returnedList = new ArrayList<>();

        for (Subscription sub : listOfSubscription) {
            String imgUrl = artistRepository.getArtistByName(sub.getArtist()).getImgUrl();
            SubscriptionDto dto = SubscriptionDto.builder()
                    .title(sub.getTitle())
                    .artist(sub.getArtist())
                    .year(sub.getYear())
                    .imgUrl(imgUrl)
                    .build();
            returnedList.add(dto);
        }
        return returnedList;
    }


    public void subscribe(SubscriptionDto subscriptionDto) {
        Subscription newSubscription = Subscription.builder()
                .title(subscriptionDto.getTitle())
                .artist(subscriptionDto.getArtist())
                .year(subscriptionDto.getYear())
                .email(subscriptionDto.getEmail())
                .build();
        subscriptionRepository.add(newSubscription);
    }


    public void unsubscribe(SubscriptionDto subscriptionDto) {
        Subscription deletedSubscription = Subscription.builder()
                .title(subscriptionDto.getTitle())
                .artist(subscriptionDto.getArtist())
                .year(subscriptionDto.getYear())
                .email(subscriptionDto.getEmail())
                .build();
        subscriptionRepository.delete(deletedSubscription);
    }
}


package com.example.demo.albamon.controller;


import com.example.demo.albamon.domain.Albamon;
import com.example.demo.albamon.repository.AlbamonRepository;
import lombok.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Transactional
public class AlbamonController {

    private final AlbamonRepository albamonRepository;

    @PostMapping("/albamon")
    public List<Albamon> getAlbamon(@RequestBody List<LocationDto> LocationDtos) {
        if (LocationDtos.size() != 2) {
            throw new IllegalArgumentException("Exactly two coordinates are required to define a rectangle.");
        }

        LocationDto coord1 = LocationDtos.get(0);
        LocationDto coord2 = LocationDtos.get(1);

        double lat1 = Math.min(coord1.getLat(), coord2.getLat());
        double lat2 = Math.max(coord1.getLat(), coord2.getLat());
        double lng1 = Math.min(coord1.getLng(), coord2.getLng());
        double lng2 = Math.max(coord1.getLng(), coord2.getLng());


        List<Albamon> albamons = albamonRepository
                .findByLocationInRectangle(lat1, lat2,
                        lng1, lng2);

        /*
        List<AlbamonDto> albamonDtos = albamons.stream()
                .map(a -> new AlbamonDto(a))
                .collect(Collectors.toList());
        */

        return albamons;

    }


    @Data
    @Getter
    @AllArgsConstructor
    public static class LocationDto{
        private double lat;
        private double lng;


    }

    @Data
    @Getter
    public static class AlbamonDto {
        public String title;
        public String company;

        public AlbamonDto(Albamon albamon) {
            this.title = albamon.getTitle();
            this.company = albamon.getCompany();
        }

    }


}

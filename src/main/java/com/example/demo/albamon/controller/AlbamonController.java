package com.example.demo.albamon.controller;


import com.example.demo.albamon.domain.Albamon;
import com.example.demo.albamon.repository.AlbamonRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Transactional
public class AlbamonController {

    private final AlbamonRepository albamonRepository;

    @GetMapping("/albamon")
    public List<Albamon> getAlbamon(@RequestBody LoctionDto LocationDto) {


        List<Albamon> albamons = albamonRepository
                .findByLocationInRectangle(LocationDto.lat1, LocationDto.lng1,
                        LocationDto.lat2, LocationDto.lng2);

        /*
        List<AlbamonDto> albamonDtos = albamons.stream()
                .map(a -> new AlbamonDto(a))
                .collect(Collectors.toList());
        */

        return albamons;

    }


    @Data
    @Getter
    public static class LoctionDto{
        private double lat1;
        private double lng1;
        private double lat2;
        private double lng2;
        public LoctionDto(double lat1, double lng1, double lat2, double lng2) {
            this.lat1 = lat1;
            this.lng1 = lng1;
            this.lat2 = lat2;
            this.lng2 = lng2;
        }
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

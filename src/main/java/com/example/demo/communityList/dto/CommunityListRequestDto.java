package com.example.demo.communityList.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommunityListRequestDto {
    private String placeName;
    private String userId;
    private String x;
    private String y;
}

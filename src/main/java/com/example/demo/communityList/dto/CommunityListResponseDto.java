package com.example.demo.communityList.dto;

import com.example.demo.community.dto.CommunityResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommunityListResponseDto {
    private String placeName;
    private String x;
    private String y;
    private List<CommunityResponseDto> communities;
}

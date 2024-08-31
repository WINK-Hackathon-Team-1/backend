package com.example.demo.community.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommunityRequestDto {
    private String userId;
    private String title;
    private String content;
}

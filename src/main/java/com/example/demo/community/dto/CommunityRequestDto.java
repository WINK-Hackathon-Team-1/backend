package com.example.demo.community.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommunityRequestDto {
    private Long listId;
    private String title;
    private String content;
}

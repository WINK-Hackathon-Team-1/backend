package com.example.demo.community.dto;

import com.example.demo.community.domain.Community;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommunityResponseDto {
    private Long id;
//    private String writerName;
    private String title;
    private String content;

    public static CommunityResponseDto create(Community community) {
        return CommunityResponseDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .content(community.getContent())
                .build();
    }
}

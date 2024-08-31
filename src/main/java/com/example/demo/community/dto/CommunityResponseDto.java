package com.example.demo.community.dto;

import com.example.demo.community.domain.Community;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommunityResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createTime;

    public static CommunityResponseDto create(Community community) {
        return CommunityResponseDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .content(community.getContent())
                .createTime(community.getCreateTime())
                .build();
    }
}

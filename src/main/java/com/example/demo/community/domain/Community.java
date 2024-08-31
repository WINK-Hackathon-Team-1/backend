package com.example.demo.community.domain;

import com.example.demo.community.dto.CommunityRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "\"community\"")
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    // 경도
    private String x;

    // 위도
    private String y;

    private LocalDateTime createTime;

    public Community update(CommunityRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.x = requestDto.getX();
        this.y = requestDto.getY();
        return this;
    }
}

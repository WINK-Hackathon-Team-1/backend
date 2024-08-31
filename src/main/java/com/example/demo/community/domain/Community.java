package com.example.demo.community.domain;

import com.example.demo.community.dto.CommunityRequestDto;
import jakarta.persistence.*;
import lombok.*;

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

    //todo: 되면 카테고리 추가

    //todo: 사용자 ManytoOne 매핑

    public Community update(CommunityRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return this;
    }
}

package com.example.demo.community.domain;

import com.example.demo.community.dto.CommunityRequestDto;
import com.example.demo.communityList.domain.CommunityList;
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

    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private CommunityList communityList;

    public Community update(CommunityRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return this;
    }
}

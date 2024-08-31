package com.example.demo.communityList.domain;

import com.example.demo.community.domain.Community;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "\"community_list\"")
@Entity
public class CommunityList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeName;

    // 경도
    private String x;

    // 위도
    private String y;

    @OneToMany(mappedBy = "communityList")
    private List<Community> communities = new ArrayList<>();
}

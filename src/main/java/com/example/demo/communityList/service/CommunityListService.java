package com.example.demo.communityList.service;

import com.example.demo.community.domain.Community;
import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.community.service.CommunityService;
import com.example.demo.communityList.domain.CommunityList;
import com.example.demo.communityList.dto.CommunityListRequestDto;
import com.example.demo.communityList.dto.CommunityListResponseDto;
import com.example.demo.communityList.repository.CommunityListRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityListService {

    private final CommunityListRepository communityListRepository;

    public CommunityListResponseDto getCommunityList(Long id){
        CommunityList communityList = communityListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid communityList id"));
        List<CommunityResponseDto> communities = new ArrayList<>();
        if(!communityList.getCommunities().isEmpty()) {
            for (Community community : communityList.getCommunities()) {
                communities.add(CommunityResponseDto.create(community));
            }
        }
        return CommunityListResponseDto.builder()
                .placeName(communityList.getPlaceName())
                .x(communityList.getX())
                .y(communityList.getY())
                .communities(communities)
                .build();
    }

    public void addCommunityList(CommunityListRequestDto requestDto) {
        communityListRepository.save(CommunityList.builder()
                        .placeName(requestDto.getPlaceName())
                        .x(requestDto.getX())
                        .y(requestDto.getY())
                        .build());
    }

    public void deleteCommunityList(Long id){
        communityListRepository.deleteById(id);
    }
}

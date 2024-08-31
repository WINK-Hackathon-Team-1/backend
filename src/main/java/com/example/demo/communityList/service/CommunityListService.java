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
    private final MemberRepository memberRepository;

    public List<CommunityListResponseDto> getCommunityList(String userId){
        Member member = memberRepository.findByUserId(userId).get(0);
        List<CommunityList> communityLists = member.getCommunityList();
        List<CommunityListResponseDto> response = new ArrayList<>();
        for (CommunityList communityList : communityLists) {
            List<CommunityResponseDto> communities = new ArrayList<>();
            for (Community community : communityList.getCommunities()) {
                communities.add(CommunityResponseDto.create(community));
            }

            response.add(CommunityListResponseDto.builder()
                            .id(communityList.getId())
                            .placeName(communityList.getPlaceName())
                            .x(communityList.getX())
                            .y(communityList.getY())
                            .communities(communities)
                    .build());
        }
        return response;
    }

    public List<CommunityResponseDto> getAllCommunityListByUserId(String userId){
        List<CommunityListResponseDto> list = getCommunityList(userId);
        List<CommunityResponseDto> response = new ArrayList<>();
        for (CommunityListResponseDto communityListResponseDto : list) {
            response.addAll(communityListResponseDto.getCommunities());
        }
        return response;
    }

    public void addCommunityList(CommunityListRequestDto requestDto) {
        Member member = memberRepository.findByUserId(requestDto.getUserId()).get(0);
        communityListRepository.save(CommunityList.builder()
                        .placeName(requestDto.getPlaceName())
                        .member(member)
                        .x(requestDto.getX())
                        .y(requestDto.getY())
                        .build());
    }

    public void deleteCommunityList(Long id){
        communityListRepository.deleteById(id);
    }
}

package com.example.demo.community.service;

import com.example.demo.community.domain.Community;
import com.example.demo.community.dto.CommunityRequestDto;
import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.community.repository.CommunityRepository;
import com.example.demo.communityList.domain.CommunityList;
import com.example.demo.communityList.repository.CommunityListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityListRepository communityListRepository;

    public CommunityResponseDto findCommunity(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid community Id"));
        return CommunityResponseDto.create(community);
    }

    public void writeCommunity(CommunityRequestDto communityRequestDto) {
        CommunityList communityList = communityListRepository.findById(communityRequestDto.getListId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid communityList id"));

        Community community = Community.builder()
                .title(communityRequestDto.getTitle())
                .content(communityRequestDto.getContent())
                .communityList(communityList)
                .createTime(LocalDate.now())
                .build();
        communityRepository.save(community);
    }

    public void update(Long id, CommunityRequestDto communityRequestDto) {
        Community community = communityRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid community id" + id));

        communityRepository.save(community.update(communityRequestDto));
    }

    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}

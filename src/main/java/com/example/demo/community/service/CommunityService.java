package com.example.demo.community.service;

import com.example.demo.community.domain.Community;
import com.example.demo.community.dto.CommunityRequestDto;
import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public List<CommunityResponseDto> findAll() {
        List<Community> communities = communityRepository.findAll();
        List<CommunityResponseDto> response = new ArrayList<>();
        for (Community community : communities) {
            response.add(CommunityResponseDto.create(community));
        }
        return response;
    }

    public void writeCommunity(CommunityRequestDto communityRequestDto) {
        Community community = Community.builder()
                .title(communityRequestDto.getTitle())
                .content(communityRequestDto.getContent())
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

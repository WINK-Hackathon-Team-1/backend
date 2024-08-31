package com.example.demo.community.service;

import com.example.demo.community.domain.Community;
import com.example.demo.community.dto.CommunityRequestDto;
import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.community.repository.CommunityRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;

    public List<CommunityResponseDto> findAll() {
        List<Community> communities = communityRepository.findAll();
        List<CommunityResponseDto> response = new ArrayList<>();
        for (Community community : communities) {
            Member member = community.getMember();
            String memberName = Objects.isNull(member) ? "" : member.getName();
            response.add(CommunityResponseDto.create(community, memberName));
        }
        return response;
    }

    public void writeCommunity(CommunityRequestDto communityRequestDto) {
        Member member = memberRepository.findByUserId(communityRequestDto.getUserId()).get(0);
        Community community = Community.builder()
                .member(member)
                .title(communityRequestDto.getTitle())
                .content(communityRequestDto.getContent())
                .build();
        communityRepository.save(community);
    }

    public void update(Long id, CommunityRequestDto communityRequestDto) {
        Community community = communityRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid community id" + id));
        if (!communityRequestDto.getUserId().equals(community.getMember().getUserId())) {
            throw new IllegalArgumentException("작성자가 아닙니다");
        }
        communityRepository.save(community.update(communityRequestDto, community.getMember()));
    }

    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}

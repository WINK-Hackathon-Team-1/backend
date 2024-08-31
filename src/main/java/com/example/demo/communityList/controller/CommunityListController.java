package com.example.demo.communityList.controller;

import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.communityList.dto.CommunityListRequestDto;
import com.example.demo.communityList.dto.CommunityListResponseDto;
import com.example.demo.communityList.service.CommunityListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communityList")
public class CommunityListController {

    private final CommunityListService communityListService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CommunityListResponseDto>> getCommunityList(@PathVariable String userId) {
        return ResponseEntity.ok(communityListService.getCommunityList(userId));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CommunityResponseDto>> getAllCommunityListByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(communityListService.getAllCommunityListByUserId(userId));
    }

    @PostMapping("")
    public ResponseEntity<?> addCommunityList(@RequestBody CommunityListRequestDto communityListRequestDto) {
        communityListService.addCommunityList(communityListRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommunityList(@PathVariable Long id) {
        communityListService.deleteCommunityList(id);
        return ResponseEntity.ok().build();
    }
}

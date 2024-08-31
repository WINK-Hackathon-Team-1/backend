package com.example.demo.community.controller;

import com.example.demo.community.dto.CommunityRequestDto;
import com.example.demo.community.dto.CommunityResponseDto;
import com.example.demo.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("")
    public ResponseEntity<List<CommunityResponseDto>> getCommunityList() {
        return ResponseEntity.ok(communityService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> writeCommunity(@RequestBody CommunityRequestDto communityRequestDto) {
        communityService.writeCommunity(communityRequestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCommunity(@PathVariable Long id, @RequestBody CommunityRequestDto communityRequestDto) {
        communityService.update(id, communityRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long id) {
        communityService.delete(id);
        return ResponseEntity.ok().build();
    }
}

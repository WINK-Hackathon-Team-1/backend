package com.example.demo.communityList.repository;

import com.example.demo.communityList.domain.CommunityList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityListRepository extends JpaRepository<CommunityList, Long> {
}

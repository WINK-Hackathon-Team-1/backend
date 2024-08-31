package com.example.demo.albamon.repository;

import com.example.demo.albamon.domain.Albamon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AlbamonRepository extends JpaRepository<Albamon, Long> {

    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) " +
            "+ sin(radians(:latitude)) * sin(radians(latitude)))) AS distance " +
            "FROM albamon " +
            "HAVING distance < 5 " +
            "ORDER BY distance "+
            "LIMIT 100", nativeQuery = true)
    List<Albamon> findByLocationWithinRadius(@Param("latitude") double latitude1, @Param("longitude") double longitude1);

    @Query(value = "SELECT * " +
            "FROM albamon " +
            "WHERE latitude BETWEEN :lat1 AND :lat2 " +
            "AND longitude BETWEEN :lng1 AND :lng2 " +
            "LIMIT 50", nativeQuery = true)
    List<Albamon> findByLocationInRectangle(
            @Param("lat1") double lat1,
            @Param("lat2") double lat2,
            @Param("lng1") double lng1,
            @Param("lng2") double lng2);
}

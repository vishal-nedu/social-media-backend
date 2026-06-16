package com.example.social_media_backend;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface followersrepo  extends JpaRepository<followers,Integer>
{
   @Query("""
       SELECT f.followingId
       FROM followers f
       WHERE f.followersId = :id
       """)
   List<Integer> findFollowingIdsByFollowerId(
           @Param("id") Integer id);

   @Transactional
   long deleteByFollowersIdAndFollowingId(
           Integer followerId,
           Integer followingId);

   boolean existsByFollowersIdAndFollowingId(
           Integer followerId,
           Integer followingId
   );
}

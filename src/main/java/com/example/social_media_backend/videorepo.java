package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface videorepo extends JpaRepository<videos,Integer>
{
    List<videos> findAll();
    List<videos> findBycategoryId(int categoryId);
    List<videos> findByUserId(int id);
    @Query("""
       SELECT v
       FROM videos v
       WHERE LOWER(v.video_name) LIKE LOWER(CONCAT('%', :title, '%'))
       """)
    List<videos> searchVideos(@Param("title") String title);
}

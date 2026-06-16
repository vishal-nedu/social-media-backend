package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface likesrepo  extends JpaRepository<likes,Integer>
{
     boolean existsByUserIdAndVideoId(
            Integer userId,
            Integer videoId
    );

    void deleteByUserIdAndVideoId(
            Integer userId,
            Integer videoId
    );

    int countByVideoId(
            Integer videoId
    );
}

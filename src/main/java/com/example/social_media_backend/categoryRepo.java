package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface categoryRepo extends JpaRepository<category,Integer>
{
    List<category> findAll();
}

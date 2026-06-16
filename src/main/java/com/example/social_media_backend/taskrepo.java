package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface taskrepo extends JpaRepository<task_of_day,Integer>
{
    task_of_day findBytaskDate(LocalDate date);
}

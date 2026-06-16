package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface questionrepo  extends JpaRepository<question_of_day,Integer> 
{
   // question_of_day findByDate(Date date);
    question_of_day findById(int id);
    question_of_day findByQuestionDate(LocalDate question_date);
}

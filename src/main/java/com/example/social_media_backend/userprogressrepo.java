package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface userprogressrepo  extends JpaRepository<user_progress,Integer>
{
    user_progress findByUserId(int id);

//    @Query("select s from  user_progress   where s.id= :id and s.progress_date= :date")
    user_progress findByUserIdAndProgressDate( int id, LocalDate date);


//    @Query("select * from  user_progress s  where s.id= :id and s.progress_date= :date")
//    user_progress findByIdandDate(@Param("id") int id, @Param("date")LocalDate date);

//    @Modifying
//    @Query("update user_progress s set question_completed= :true where s.id= :id and s.progress_date= :date")
//    boolean updateQuestion_complete(@Param("id") int id, @Param("date")LocalDate date);
//    @Modifying
//    @Query("update user_progress s set task_completed= :true where s.id= :id and s.progress_date= :date")
//    boolean updateTask_complete(@Param("id") int id, @Param("date")LocalDate date);
}

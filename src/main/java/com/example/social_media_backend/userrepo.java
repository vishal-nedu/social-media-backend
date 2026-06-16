package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userrepo  extends JpaRepository<users,Integer>
{
    users findByUsernameAndPassword(String username,String password);
    users findByUsername(String username);
    users findById(int id);
    List<users> findByUsernameContainingIgnoreCase(String username);

    @Query("""
       SELECT u
       FROM users u
       WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :name, '%'))
       AND u.id <> :userId
       """)
    List<users> searchUsers(
            @Param("name") String name,
            @Param("userId") int userId);
}

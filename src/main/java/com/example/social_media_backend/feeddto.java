package com.example.social_media_backend;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class feeddto
{
    Integer videoId;
    String title;
    String description;
    String video_path;
    Integer userId;
    String username;
    String profile_photo;
    Integer likesCount;
    boolean liked;


}

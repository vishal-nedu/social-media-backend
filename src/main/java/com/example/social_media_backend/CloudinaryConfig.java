package com.example.social_media_backend;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig
{
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxmzk1s40",
                "api_key", "368929226492681",
                "api_secret", "ebczeslg51D1dkD7DCYuECi_w3M"
        ));
    }
}

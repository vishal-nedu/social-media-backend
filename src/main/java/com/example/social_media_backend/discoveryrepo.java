package com.example.social_media_backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface discoveryrepo  extends JpaRepository<discovery,Integer>
{
    discovery findByDiscoveryDate(LocalDate date);
}

package com.example.social_media_backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class followers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Override
    public String toString() {
        return "followers{" +
                "id=" + id +
                ", followersId=" + followersId +
                ", followingId=" + followingId +
                '}';
    }

    @Column(name = "follower_id")
    int followersId;
    @Column(name = "following_id")
    int followingId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowersId() {
        return followersId;
    }

    public void setFollowersId(int followersId) {
        this.followersId = followersId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }
}

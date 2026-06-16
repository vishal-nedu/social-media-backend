package com.example.social_media_backend;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private categoryRepo categoryRepo;
    @Autowired
    private videorepo videorepo;
    @Autowired
    private userrepo userrepo;
    @Autowired
    private taskrepo taskrepo;
    @Autowired
    private userprogressrepo userprogressrepo;
    @Autowired
    private followersrepo followersrepo;

    @Autowired
    private questionrepo questionrepo;
    @Autowired
    private likesrepo likesrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private discoveryrepo discoveryrepo;
    @Autowired
    private Cloudinary cloudinary;
    public List<category> getcategory()
    {
        return categoryRepo.findAll();
    }

    public List<feeddto> getvideos(Integer userid)
    {
        List<videos> v= videorepo.findAll();
        List<feeddto> ans=new ArrayList<>();
        for(videos v1:v)
        {
            Optional<users> u1=userrepo.findById(v1.getUserId());
            feeddto dto=new feeddto();
            dto.setUsername(u1.get().getUsername());
            dto.setTitle(v1.getVideo_name());
            dto.setVideoId(v1.getId());
            dto.setUserId(u1.get().getId());
            dto.setProfile_photo(u1.get().getProfile_photo());
            dto.setVideo_path(v1.getVideo_path());
            dto.setDescription(v1.getDescription());
            dto.setLiked(likesrepo.existsByUserIdAndVideoId(userid,v1.getId()));
//            System.out.println(likesrepo.countByVideoId(v1.getId()));
            dto.setLikesCount(likesrepo.countByVideoId(v1.getId()));
            ans.add(dto);
        }
        return ans;
    }

    public List<feeddto> getvideosbycategory_id(int id, Integer userid)
    {
        List<videos>v = videorepo.findBycategoryId(id);
        List<feeddto> ans=new ArrayList<>();
        for(videos v1:v)
        {
            Optional<users> u1=userrepo.findById(v1.getUserId());
            feeddto dto=new feeddto();
            dto.setUsername(u1.get().getUsername());
            dto.setTitle(v1.getVideo_name());
            dto.setVideoId(v1.getId());
            dto.setUserId(u1.get().getId());
            dto.setProfile_photo(u1.get().getProfile_photo());
            dto.setVideo_path(v1.getVideo_path());
            dto.setDescription(v1.getDescription());
            dto.setLiked(likesrepo.existsByUserIdAndVideoId(userid,v1.getId()));
//            System.out.println(likesrepo.countByVideoId(v1.getId()));
            dto.setLikesCount(likesrepo.countByVideoId(v1.getId()));
            ans.add(dto);
        }
        return ans;
    }

    public users register_user(users user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userrepo.save(user);
    }

    public users login_user(String username, String password)
    {
        users u= userrepo.findByUsername(username);
        if(passwordEncoder.matches(password,u.getPassword()))
        {
            return u;
        }
        return null;
    }

    public users getuserbyid(int id)
    {
      users u1= userrepo.findById(id);
        System.out.println(u1);
        return u1;
    }

    public question_of_day todayquestion()
    {
        LocalDate date=LocalDate.now();
        return questionrepo.findByQuestionDate(date);
    }


    public boolean todayanswer(answerrequest answerrequest)
    {
        int id=answerrequest.getQuestionid();
        question_of_day q1=questionrepo.findById(id);
        String an=answerrequest.getAns();
        if(an.equals(q1.getAnswer()))
        {
            return true;
        }
        return false;
    }

    public task_of_day todaytask()
    {
        LocalDate date=LocalDate.now();
        return taskrepo.findBytaskDate(date);
    }

    public user_progress getday(int id)
    {
        return userprogressrepo.findByUserId(id);
    }

    public boolean question_complete(int id)
    {
        LocalDate date=LocalDate.now();
        user_progress u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
        if(u1==null)
        {
            u1=new user_progress();
            u1.setUserId(id);
            u1.setProgressDate(date);
            u1.setQuestionCompleted(true);
             userprogressrepo.save(u1);
             return true;

        }
        else {
            u1.setQuestionCompleted(true);
            userprogressrepo.save(u1);
            return true;
        }

        //return userprogressrepo.updateQuestion_complete(id,date);
    }

    public boolean task_complete(int id)
    {
        LocalDate date=LocalDate.now();
        user_progress u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
        if(u1==null)
        {
            u1=new user_progress();
            u1.setUserId(id);
            u1.setProgressDate(date);
            u1.setTaskCompleted(true);
            userprogressrepo.save(u1);
            return true;

        }
        else {
            u1.setTaskCompleted(true);
            userprogressrepo.save(u1);
            return true;
        }
    }

    public boolean question_completed(int id)
    {
        LocalDate date=LocalDate.now();
        user_progress u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
        if(u1==null)
        {


            return false;

        }
        else {

            if(u1.isQuestionCompleted())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean task_completed(int id)
    {
        LocalDate date=LocalDate.now();
        user_progress u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
        if(u1==null)
        {


            return false;

        }
        else {

            if(u1.isTaskCompleted())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public int getstreak(int id)
    {
        LocalDate date=LocalDate.now();
        int streak=0;
        user_progress u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
        if(u1!=null)
        {
            while(true)
            {
                if(u1.isTaskCompleted()&& u1.isQuestionCompleted())
                {
                    streak+=1;
                }
                date=date.minusDays(1);
                u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
                if(u1==null)
                {
                    return streak;
                }
            }
        }
        else
        {
            date=date.minusDays(1);;
            u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
            if(u1!=null)
            {
                while(true)
                {
                    if(u1.isTaskCompleted()&& u1.isQuestionCompleted())
                    {
                        streak+=1;
                    }
                    date=date.minusDays(1);;
                    u1=userprogressrepo.findByUserIdAndProgressDate(id,date);
                    if(u1==null)
                    {
                        return streak;
                    }
                }
            }

        }
        return 0;
    }

    public users upload(MultipartFile file, int id) throws IOException {
        users u1=userrepo.findById(id);
        if(u1==null)
        {
            throw  new RuntimeException("user not found");
        }
        if (file.isEmpty()) {
            throw new RuntimeException("File not selected");
        }
//        String fileName = file.getOriginalFilename();
//        Path uploadDir = Paths.get(
//                "uploads/profile/"
//        );
//        if(!Files.exists(uploadDir))
//        {
//            Files.createDirectories(uploadDir);
//        }
//        Path path=uploadDir.resolve(file.getOriginalFilename());
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "image"
                ));

        String imageUrl = uploadResult.get("secure_url").toString();

//        Files.write(path, file.getBytes());
        u1.setProfile_photo(imageUrl);
       // System.out.println(fileName);
        userrepo.save(u1);
        return u1;
    }


    public videos uploadvideo(MultipartFile file, String title, String description, int categoryid, int id) throws IOException {

        if(file.isEmpty())
        {
            throw new RuntimeException("Video not selected");
        }
//        String fileName = file.getOriginalFilename();
//
//        Path uploadDir = Paths.get(
//                "uploads/videos/"
//        );
//        if(!Files.exists(uploadDir))
//        {
//            Files.createDirectories(uploadDir);
//        }
//        Path path=uploadDir.resolve(fileName);
//
//        Files.write(path, file.getBytes());
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "video"
                ));

        String videoUrl = uploadResult.get("secure_url").toString();

        videos v1=new videos();
        v1.setUserId(id);
        v1.setCategoryId(categoryid);
        v1.setVideo_name(title);
        v1.setVideo_path(videoUrl);
        v1.setDescription(description);
        v1.setDate_posted(LocalDate.now());
       return  videorepo.save(v1);
    }

    public List<feeddto> getvideosbyuser(int id)
    {
        List<videos> v= videorepo.findByUserId(id);
        List<feeddto> ans=new ArrayList<>();
        for(videos v1:v)
        {
            Optional<users> u1=userrepo.findById(v1.getUserId());
            feeddto dto=new feeddto();
            dto.setUsername(u1.get().getUsername());
            dto.setTitle(v1.getVideo_name());
            dto.setVideoId(v1.getId());
            dto.setUserId(u1.get().getId());
            dto.setProfile_photo(u1.get().getProfile_photo());
            dto.setVideo_path(v1.getVideo_path());
            dto.setDescription(v1.getDescription());
            ans.add(dto);
            dto.setLiked(likesrepo.existsByUserIdAndVideoId(id,v1.getId()));
            dto.setLikesCount(likesrepo.countByVideoId(v1.getId()));
        }
        return ans;
    }

    public List<usersearch> searchuser(String title,int id)
    {
        List<users> l1= userrepo.searchUsers(title,id);
        List<Integer> followingIds =
                followersrepo.findFollowingIdsByFollowerId(id);
        List<usersearch> ls=new ArrayList<>();
        for(users u:l1)
        {
            usersearch us=new usersearch();
            us.setId(u.getId());
            us.setUsername(u.getUsername());
            us.setProfile_photo(u.getProfile_photo());

            us.setFollowing(followingIds.contains(u.getId()));
            ls.add(us);
        }

        return ls;
    }

    public String follow(Integer followerId, Integer followingId)
    {
        followers f1=new followers();
        f1.setFollowersId(followerId);
        f1.setFollowingId(followingId);
        followers f2= followersrepo.save(f1);
        System.out.println(f2);
        return "Followed";
    }
    @Transactional

    public String unfollow(Integer followerId, Integer followingId)
    {
        long v= followersrepo.deleteByFollowersIdAndFollowingId(followerId,followingId);
        if(v>0)
        {
            return "unfollowed";
        }
        return "Not found";
    }

    public boolean doesfollow(Integer followerId, Integer followingId)
    {
        return followersrepo.existsByFollowersIdAndFollowingId(followerId,followingId);
    }
    @Transactional

    public boolean toggleLike(Integer userId, Integer videoId)
    {
        if(
                likesrepo.existsByUserIdAndVideoId(
                        userId,
                        videoId
                ))
        {
            likesrepo.deleteByUserIdAndVideoId(
                    userId,
                    videoId
            );

            return false;
        }

        likes l=new likes();

        l.setUserId(userId);

        l.setVideoId(videoId);

        likesrepo.save(l);

        return true;
    }

    public boolean existsByUserIdAndVideoId(Integer userid, Integer videoid)
    {
        return likesrepo.existsByUserIdAndVideoId(userid,videoid);
    }

    public int countByVideoId(Integer videoId)
    {
        return likesrepo.countByVideoId(videoId);
    }

    public discovery getdiscovery()
    {
        return discoveryrepo.findByDiscoveryDate(LocalDate.now());
    }
}

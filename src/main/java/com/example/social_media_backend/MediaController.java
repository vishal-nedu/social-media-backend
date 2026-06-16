package com.example.social_media_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MediaController
{
    @Autowired
    private MediaService mediaService;
    @RequestMapping("/category")
    @CrossOrigin
    public List<category> getcategory()
    {
        List<category> ls=mediaService.getcategory();
        return ls;

    }
    @RequestMapping("/videos/{userid}")
    @CrossOrigin
    public List<feeddto> getvideos(@PathVariable Integer userid)
    {
        List<feeddto> ls=mediaService.getvideos(userid);
        return ls;
    }
    @GetMapping("/videos/{id}/{userid}")
    @CrossOrigin
    public List<feeddto> getvideosbycategory_id(@PathVariable int id,@PathVariable Integer userid)
    {
        List<feeddto> ls=mediaService.getvideosbycategory_id(id,userid);
        return ls;
    }
    @PostMapping("/register")
    @CrossOrigin
    public users register(@RequestBody users user)
    {
        System.out.println(user);
        return mediaService.register_user(user);
    }
    @PostMapping("/login")
    @CrossOrigin
    public users login(@RequestBody users user)
    {
        return mediaService.login_user(user.getUsername(),user.getPassword());
    }
    @GetMapping("/user/{id}")
    @CrossOrigin
    public users getuserbyid(@PathVariable int id)
    {
        return mediaService.getuserbyid(id);
    }
    @GetMapping("/question")
    @CrossOrigin
    public question_of_day todayquestion()
    {
        return mediaService.todayquestion();
    }
    @GetMapping("/task")
    @CrossOrigin
    public task_of_day todaytask()
    {
        return mediaService.todaytask();
    }
    @PostMapping("/check-answer")
    @CrossOrigin
    public boolean todayanswer(@RequestBody answerrequest answerrequest)
    {
        return mediaService.todayanswer(answerrequest);
    }
    @GetMapping("/day/{id}")
    @CrossOrigin
    public user_progress getday(@PathVariable int id)
    {
        return mediaService.getday(id);
    }
    @GetMapping("/question-complete/{id}")
    @CrossOrigin
    public boolean question_complete(@PathVariable int id)
    {
       return  mediaService.question_complete(id);
    }
    @GetMapping("/task-complete/{id}")
    @CrossOrigin
    public boolean task_complete(@PathVariable int id)
    {
        return  mediaService.task_complete(id);
    }
    @GetMapping("/question-completed/{id}")
    @CrossOrigin
    public boolean question_completed(@PathVariable int id)
    {
        return  mediaService.question_completed(id);
    }
    @GetMapping("/task-completed/{id}")
    @CrossOrigin
    public boolean task_completed(@PathVariable int id)
    {
        return  mediaService.task_completed(id);
    }

    @GetMapping("/streak/{id}")
    @CrossOrigin
    public int getStreak(@PathVariable int id)
    {
        return  mediaService.getstreak(id);
    }

    @PostMapping("/upload-profile/{id}")
    @CrossOrigin
    public users upload(@RequestParam("file") MultipartFile file,@PathVariable int id) throws IOException {
        return mediaService.upload(file,id);
    }
    @PostMapping("/upload-video/{id}")
    @CrossOrigin
    public videos uploadvideo(@RequestParam MultipartFile file, @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam int categoryid,@PathVariable int id) throws IOException {
        return mediaService.uploadvideo(file,title,description,categoryid,id);
    }
    @GetMapping("/uservideos/{userid}")
    @CrossOrigin
    public List<feeddto> getvideosbyuser(@PathVariable int userid)
    {
        return  mediaService.getvideosbyuser(userid);
    }

    @GetMapping("/searchusers/{title}/{id}")
    @CrossOrigin
    public List<usersearch> searchusers(@PathVariable String title,@PathVariable int id)
    {
        return mediaService.searchuser(title,id);
    }
    @PostMapping("/follow/{followerId}/{followingId}")
    @CrossOrigin
    public String follow(@PathVariable Integer followerId,@PathVariable Integer followingId )
    {
        System.out.println(followerId);
        System.out.println(followingId);
        return mediaService.follow(followerId,followingId);
    }
    @DeleteMapping("/unfollow/{followerId}/{followingId}")
    @CrossOrigin
    public String unfollow(@PathVariable Integer followerId,@PathVariable Integer followingId )
    {
        System.out.println("in delte method:"+followerId);
        System.out.println("in delte method:"+followingId);
        return mediaService.unfollow(followerId,followingId);
    }
    @GetMapping("/doesfollow/{followerId}/{followingId}")
    @CrossOrigin
    public boolean doesfollow(@PathVariable Integer followerId,@PathVariable Integer followingId )
    {
        //System.out.println("in delte method:"+followerId);
//        System.out.println("in delte method:"+followingId);
        return mediaService.doesfollow(followerId,followingId);
    }
    @PostMapping("/like/{userid}/{videoid}")
    @CrossOrigin

    public boolean like(@PathVariable int userid, @PathVariable int videoid)
    {
        return mediaService.toggleLike(userid, videoid);
    }

    @GetMapping("/liked/{userid}/{videoid}")
    @CrossOrigin

    public boolean isliked(@PathVariable int userid, @PathVariable int videoid)
    {
        return mediaService.existsByUserIdAndVideoId(userid,videoid);
    }

    @GetMapping(
            "/likecount/{videoid}"
    )
    @CrossOrigin

    public int count(@PathVariable int videoid)
    {
        return mediaService.countByVideoId(videoid);
    }


    @GetMapping("/discovery")
    @CrossOrigin
    public discovery getdiscovery()
    {
        return mediaService.getdiscovery();
    }


}

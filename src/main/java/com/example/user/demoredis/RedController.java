package com.example.user.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/myredis", method = RequestMethod.GET, produces = "application/json")
public class RedController {


        private static final Logger logger = LogManager.getLogger(RedController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addUser")
    public String addUser() {

        User caroline = new User("1","Caroline","Hawford","carolinehawford@gmail.com","123",User.Gender.FEMALE,"Bank street 128-55 ");
        User tim = new User("2","Tim","MayBeach","timmay@gmail.com","123",User.Gender.MALE,"XYZ street 133-44 ");

        userRepository.save(caroline);
        userRepository.save(tim);

        return "Users are added";
    }




    @GetMapping("/deleteUser/{userId}")
    @CacheEvict(value = "users", allEntries=true)
    public String deleteUser(@PathVariable String userId) {

        userRepository.deleteById(userId);
        System.out.println("Deleting redis data and cache");

        return "User deleted!";
    }



    @GetMapping("/getUser")
    public String getUser() {
        List<User> user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
       return ""+user;
    }

    @Cacheable(value = "users", key = "#userId")
    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String userId) {


        return userRepository.findById(userId).get();
    }



}

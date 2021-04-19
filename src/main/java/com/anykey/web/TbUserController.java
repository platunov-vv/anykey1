package com.anykey.web;

import com.anykey.model.domain.User;
import com.anykey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class TbUserController {

    private UserService userService;


    @Autowired
    public TbUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getAll")
    public List<User> test(){
        return userService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public User newUser(@RequestBody User user){
       Random random = new Random();
      //  User user = new User();
      user.setFirstName(name);
        user.setId(String.valueOf(random.nextInt()));
        return userService.save(user);
    }


}

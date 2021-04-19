package com.anykey.web;

import com.anykey.model.domain.User;
import com.anykey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class TbUserController {

    private UserService userService;


    @Autowired
    public TbUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getAll")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String newUser(@RequestBody User user) {
        User saved = userService.save(user);
        return saved.getId();
    }


}

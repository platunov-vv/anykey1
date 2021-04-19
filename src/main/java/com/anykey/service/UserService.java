package com.anykey.service;

import com.anykey.model.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);
}

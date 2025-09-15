package com.anykey.service;

import com.anykey.model.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface        UserService {
    List<User> findAll();

    User save(User user);


    Optional<User> findById(UUID id);
}

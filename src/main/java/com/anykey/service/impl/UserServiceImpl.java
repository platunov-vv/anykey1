package com.anykey.service.impl;

import com.anykey.model.domain.User;
import com.anykey.model.rep.UserRepository;
import com.anykey.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
}

}

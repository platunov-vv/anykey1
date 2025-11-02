package com.anykey.web;

import com.anykey.model.domain.User;
import com.anykey.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class TbUserController {

    private UserService userService;


    @Autowired
    public TbUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    /* @PutMapping("/{id}")
   public String updateUser(@PathVariable String id, @RequestBody User user) {
        User saved = userService.save(user);
        return saved.getId();
    }*/
   /* public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return updatedUser.findById(id)
                .map(existingItem -> {
                    // Обновляем поля существующей сущности
                    existingItem.firstName(updatedUser.getName());
                    existingItem.lastName(updatedUser.getPrice());
                    // обновить другие поля...

                    itemRepository.save(existingItem);
                    return ResponseEntity.ok(existingItem);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/
    @PostMapping(value = "/add")
    public UUID newUser(@Valid @RequestBody User user) {
        User saved = userService.save(user);
        return saved.getId();
    }


}

package com.anykey.web;

import com.anykey.model.domain.User;
import com.anykey.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TbUserControllerTest {

    private UserService userService;                 // Мокаем сервис
    private TbUserController controller;             // Контроллер

    private User user1, user2;
    private UUID userId1, userId2;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        controller = new TbUserController(userService);

        userId1 = UUID.randomUUID();
        userId2 = UUID.randomUUID();

        user1 = new User();
        user1.setId(userId1);
        user1.setFirstName("Alice");

        user2 = new User();
        user2.setId(userId2);
        user2.setFirstName("Bob");
    }

    @Test
    void getUsers() {
        // Мокаем возврат списка пользователей
        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = controller.getUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getFirstName());
        assertEquals("Bob", result.get(1).getFirstName());

        verify(userService).findAll();
    }

    @Test
    void getUserById() {
        // Тестирование случая когда пользователь найден
        when(userService.findById(userId1)).thenReturn(Optional.of(user1));
        var response = controller.getUserById(userId1);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(user1, response.getBody());

        // Тестирование случая, когда пользователь не найден
        when(userService.findById(userId1)).thenReturn(Optional.empty());
        assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
            controller.getUserById(userId1);
        });

        verify(userService, times(2)).findById(userId1);
    }

    @Test
    void newUser() {
        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setFirstName("Charlie");

        when(userService.save(any(User.class))).thenReturn(newUser);

        UUID resultId = controller.newUser(newUser);
        assertEquals(newUser.getId(), resultId);

        verify(userService).save(newUser);
    }
}
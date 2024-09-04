package com.cubic.springbootcruddemo.service;

import com.cubic.springbootcruddemo.model.User;
import com.cubic.springbootcruddemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository  userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUserInfo(){
        User user = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");
        User savedUser = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");
        //mock
        when(userRepository.save(user)).thenReturn(savedUser);
        //actual calling
        User result = userService.saveUserInfo(user);
        //assertion
        assertNotNull(result);
        assertEquals(1,result.getId());

    }

    @Test
    public void testUpdateUserInfo(){
        User user = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");
        //mock
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        //actual calling
        User result = userService.updateUserInfo(1,user);
        //assertion
        assertNotNull(result);
        assertEquals(1,result.getId());
        assertEquals("Mohan",result.getFirstName());
        assertEquals("Sharma",result.getLastName());
    }
    @Test
    public void testFindAllUser(){
        User user1 = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");
        User user2 = new User(2, "Ram","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");
        List<User> users = Arrays.asList(user1,user2);


        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAllUser();

        assertEquals(2,result.size());
        assertEquals("Mohan",result.get(0).getFirstName());
        assertEquals("Sharma",result.get(0).getLastName());
    }

    @Test
    public void testFindUserById(){
        User user = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1,result.getId());
        assertEquals("Mohan",result.getFirstName());
        assertEquals("Sharma",result.getLastName());
    }

    @Test
    public void testDeleteUserById(){
        User user = new User(1, "Mohan","Sharma","mohan@gmail.com", "mohan","mohan@123","Kathmandu, Nepal");

        //mock
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);


        userService.deleteUserInfo(1);

        verify(userRepository,times(1)).delete(user);
    }







}

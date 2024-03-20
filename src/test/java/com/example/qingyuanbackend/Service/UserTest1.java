package com.example.qingyuanbackend.Service;

import com.example.qingyuanbackend.mapper.RegionMapper;
import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.Region;
import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.service.UserService;
import com.example.qingyuanbackend.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private RegionMapper regionMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
// 注册
    @Test
    public void testRegisterSuccess() {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("strongPassword");

        when(userMapper.findByUsername(newUser.getUsername())).thenReturn(null);
        doAnswer(invocation -> null).when(userMapper).insertUser(any(User.class));
        when(passwordEncoder.encode(anyString())).thenReturn("encryptedPassword");

        var response = userService.register(newUser);
        assertTrue(response.isSuccess());
        assertEquals("账号注册成功", response.getMessage());
        verify(userMapper, times(1)).insertUser(any(User.class));
    }

    @Test
    public void testUpdateUserRegionByUsername() {
        User user = new User();
        user.setUsername("existingUser");
        user.setRegion("oldRegion");

        when(userMapper.findByUsername("existingUser")).thenReturn(user);
        when(userMapper.updateUser(any(User.class))).thenReturn(1);

        var response = userService.updateUserRegionByUsername("existingUser", "newRegion");
        assertTrue((Boolean) response.get("success"));
        assertEquals("newRegion", ((User) response.get("user")).getRegion());
        verify(userMapper, times(1)).updateUser(any(User.class));
    }

    @Test
    public void testDeleteUserByUsername() {
        User user = new User();
        user.setUsername("deleteUser");

        when(userMapper.findByUsername("deleteUser")).thenReturn(user);
        when(userMapper.deleteUser("deleteUser")).thenReturn(1);

        var response = userService.deleteUserByUsername("deleteUser");
        assertTrue((Boolean) response.get("success"));
        assertNotNull(response.get("user"));
        verify(userMapper, times(1)).deleteUser("deleteUser");
    }

    @Test
    public void testGetAllUsersAndRegions() {
        when(userMapper.selectAllUsers(any())).thenReturn(Collections.singletonList(new User()));
        when(regionMapper.selectAllRegions()).thenReturn(Collections.singletonList(new Region()));

        var responseEntity = userService.getAllUsersAndRegions(1, 10);
        assertNotNull(responseEntity);
        assertTrue(responseEntity.getBody() instanceof Map);

        var body = (Map<String, Object>) responseEntity.getBody();
        assertTrue((Boolean) body.get("success"));
        assertNotNull(body.get("users"));
        assertNotNull(body.get("regions"));
    }
// 用户名已经存在
    @Test
    public void testRegisterUsernameAlreadyExists() {
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        existingUser.setPassword("password");

        when(userMapper.findByUsername("existingUser")).thenReturn(existingUser);

        User newUser = new User();
        newUser.setUsername("existingUser");
        newUser.setPassword("newPassword");

        var response = userService.register(newUser);
        assertFalse(response.isSuccess());
        assertEquals("Account already registered.", response.getMessage());
        verify(userMapper, never()).insertUser(any(User.class));
    }
// 用户没有注册
    @Test
    public void testLoginUsernameNotRegistered() {
        when(userMapper.findByUsername("nonexistentUser")).thenReturn(null);

        LoginRequest request = new LoginRequest();
        request.setUsername("nonexistentUser");
        request.setPassword("password");

        var response = userService.login(request);
        assertFalse(response.isSuccess());
        assertEquals("Account not registered.", response.getMessage());
    }
// 密码错误
    @Test
    public void testLoginIncorrectPassword() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("encryptedPassword");

        when(userMapper.findByUsername("user")).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("wrongPassword");

        var response = userService.login(request);
        assertFalse(response.isSuccess());
        assertEquals("密码错误", response.getMessage());
    }

// 用户找不到
    @Test
    public void testUpdateUserRegionByUsernameNotFound() {
        when(userMapper.findByUsername("nonexistentUser")).thenReturn(null);

        var response = userService.updateUserRegionByUsername("nonexistentUser", "newRegion");
        assertFalse((Boolean) response.get("success"));
        assertEquals("User not found", response.get("message"));
        verify(userMapper, never()).updateUser(any(User.class));
    }

    @Test
    public void testDeleteUserByUsernameNotFound() {
        when(userMapper.findByUsername("nonexistentUser")).thenReturn(null);

        var response = userService.deleteUserByUsername("nonexistentUser");
        assertFalse((Boolean) response.get("success"));
        assertEquals("User not found", response.get("message"));
    }




}
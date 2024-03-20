package com.example.qingyuanbackend.Service;
import com.example.qingyuanbackend.mapper.AbnormalityMapper;
import com.example.qingyuanbackend.mapper.FormMapper;
import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.Abnormality;
import com.example.qingyuanbackend.model.Form;
import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.service.RecentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecentServiceTest {

    @Mock
    private FormMapper formMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AbnormalityMapper abnormalityMapper;

    @InjectMocks
    private RecentService recentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRecentActivities_NormalScenario() {
        // 准备模拟数据
        Form mockForm = new Form();
        mockForm.setDate(new Date());
        mockForm.setUserid(1);
        mockForm.setAbnormalid(1);

        User mockUser = new User();
        mockUser.setUsername("testUser");

        Abnormality mockAbnormality = new Abnormality();
        mockAbnormality.setStatus(2); // 假设状态为2代表上报
        mockAbnormality.setRegionName("A");
        mockAbnormality.setLayers(1);

        when(formMapper.findRecentForms()).thenReturn(Collections.singletonList(mockForm));
        when(userMapper.findByUserid(1)).thenReturn(mockUser);
        when(abnormalityMapper.findAbnormalityById(1)).thenReturn(mockAbnormality);

        List<String> activities = recentService.getRecentActivities();

        assertFalse(activities.isEmpty());
        assertTrue(activities.get(0).contains("testUser 上报了 A区域的1层的故障"));
    }

    @Test
    public void testGetRecentActivities_NoRecentForms() {
        when(formMapper.findRecentForms()).thenReturn(Collections.emptyList());

        List<String> activities = recentService.getRecentActivities();

        assertTrue(activities.isEmpty());
    }

    @Test
    public void testGetRecentActivities_UserNotFound() {
        Form mockForm = new Form();
        mockForm.setDate(new Date());
        mockForm.setUserid(1);
        mockForm.setAbnormalid(1);

        when(formMapper.findRecentForms()).thenReturn(Collections.singletonList(mockForm));
        when(userMapper.findByUserid(1)).thenReturn(null);

        List<String> activities = recentService.getRecentActivities();

        assertTrue(activities.isEmpty()); // 或根据实际逻辑调整断言
    }


}
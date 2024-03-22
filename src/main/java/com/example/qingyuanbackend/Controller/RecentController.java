package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.service.RecentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recent")
@Tag(name = "最近信息", description = "工厂地图页面下的最近信息的接口")
public class RecentController {

    @Autowired
    private RecentService recentService;

    @GetMapping
    public ResponseEntity<List<String>> getRecentActivities() {
        List<String> activities = recentService.getRecentActivities();
        return ResponseEntity.ok(activities);
    }
}

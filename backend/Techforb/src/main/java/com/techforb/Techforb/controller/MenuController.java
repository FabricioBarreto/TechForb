package com.techforb.Techforb.controller;

import com.techforb.Techforb.dto.response.MenuResponse;
import com.techforb.Techforb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/api/menu")
    public List<MenuResponse> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }
}
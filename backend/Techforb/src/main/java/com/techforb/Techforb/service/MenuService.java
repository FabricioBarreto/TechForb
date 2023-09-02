package com.techforb.Techforb.service;

import com.techforb.Techforb.dto.response.MenuResponse;
import com.techforb.Techforb.models.Menu;

import java.util.List;

public interface MenuService {

    List<MenuResponse> getAllMenuItems();

}

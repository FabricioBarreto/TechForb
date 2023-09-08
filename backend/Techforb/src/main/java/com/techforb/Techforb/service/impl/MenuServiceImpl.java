package com.Techforb.Techforb.service.impl;

import com.Techforb.Techforb.dto.response.MenuResponse;
import com.Techforb.Techforb.mapper.MenuMapper;
import com.Techforb.Techforb.models.Menu;
import com.Techforb.Techforb.repository.MenuRepository;
import com.Techforb.Techforb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuResponse> getAllMenuItems() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(menu -> menuMapper.menuToResponse(menu))
                .collect(Collectors.toList());
    }
}

package com.Techforb.Techforb.mapper;

import com.Techforb.Techforb.dto.response.MenuResponse;
import com.Techforb.Techforb.models.Menu;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    @Autowired
    private ModelMapper mapper;

    public MenuResponse menuToResponse(Menu menu){
        return mapper.map(menu, MenuResponse.class);
    }

}

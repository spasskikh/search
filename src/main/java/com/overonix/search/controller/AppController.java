package com.overonix.search.controller;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.entity.Coordinates;
import com.overonix.search.service.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/{address}")
    List<Coordinates> search(@PathVariable String address){
        return appService.searchAndSave(address);
    }

    @GetMapping
    List<SearchResultDto> getAllFromDb(){
        return appService.getAllFromDb();
    }
}

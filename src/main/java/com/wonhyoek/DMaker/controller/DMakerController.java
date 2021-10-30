package com.wonhyoek.DMaker.controller;

import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/create-developer")
    public String createDevelopers(){
        dMakerService.createDeveloper();
        return "okay";
    }

}

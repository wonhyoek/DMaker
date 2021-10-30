package com.wonhyoek.DMaker.controller;

import com.wonhyoek.DMaker.dto.CreateDeveloper;
import com.wonhyoek.DMaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DMakerController {
    private final DMakerService dMakerService;

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(
           @Valid @RequestBody CreateDeveloper.Request request
            ) {
        log.info("request: {}", request);
        return dMakerService.createDeveloper(request);
    }

}

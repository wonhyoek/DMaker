package com.wonhyoek.DMaker.controller;

import com.wonhyoek.DMaker.dto.*;
import com.wonhyoek.DMaker.exception.DMakerException;
import com.wonhyoek.DMaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllEmployedDevelopers() {
        return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developers/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable String memberId
    ) {
        return dMakerService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(
           @Valid @RequestBody CreateDeveloper.Request request
            ) {
        log.info("request: {}", request);
        return dMakerService.createDeveloper(request);
    }

    @PutMapping("/developers/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request
    ) {
        return dMakerService.editDeveloper(memberId,request);
    }

    @DeleteMapping("/developers/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable String memberId
    ){
        return dMakerService.deleteDeveloper(memberId);
    }
}

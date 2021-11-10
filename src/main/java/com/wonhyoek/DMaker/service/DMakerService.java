package com.wonhyoek.DMaker.service;

import com.wonhyoek.DMaker.dto.CreateDeveloper;
import com.wonhyoek.DMaker.dto.DeveloperDetailDto;
import com.wonhyoek.DMaker.dto.DeveloperDto;
import com.wonhyoek.DMaker.dto.EditDeveloper;
import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.entity.RetiredDeveloper;
import com.wonhyoek.DMaker.exception.DMakerException;
import com.wonhyoek.DMaker.repository.DeveloperRepository;
import com.wonhyoek.DMaker.repository.RetiredDeveloperRepository;
import com.wonhyoek.DMaker.type.DeveloperLevel;
import com.wonhyoek.DMaker.type.StatusCode;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static com.wonhyoek.DMaker.exception.DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;
import static com.wonhyoek.DMaker.exception.DMakerErrorCode.NO_DEVELOPER;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request){

        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        if(request.getDeveloperLevel() == DeveloperLevel.SENIOR
            && request.getExperienceYears() < 10
        ) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository
                .findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId);
        return DeveloperDetailDto.fromEntity(developer);


    }
    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        Developer developer = developerRepository.findByMemberId(memberId);
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId);
        developer.setStatusCode(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .name(developer.getName())
                .memberId(memberId)
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);

    }
}

package com.wonhyoek.DMaker.service;

import com.wonhyoek.DMaker.dto.CreateDeveloper;
import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.exception.DMakerErrorCode;
import com.wonhyoek.DMaker.exception.DMakerException;
import com.wonhyoek.DMaker.repository.DeveloperRepository;
import com.wonhyoek.DMaker.type.DeveloperLevel;
import com.wonhyoek.DMaker.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static com.wonhyoek.DMaker.exception.DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request){

        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
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
}

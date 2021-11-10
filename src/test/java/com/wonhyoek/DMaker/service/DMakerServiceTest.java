package com.wonhyoek.DMaker.service;

import com.wonhyoek.DMaker.dto.DeveloperDetailDto;
import com.wonhyoek.DMaker.dto.DeveloperDto;
import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.repository.DeveloperRepository;
import com.wonhyoek.DMaker.repository.RetiredDeveloperRepository;
import com.wonhyoek.DMaker.type.DeveloperLevel;
import com.wonhyoek.DMaker.type.DeveloperSkillType;
import com.wonhyoek.DMaker.type.StatusCode;
import org.assertj.core.internal.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DMakerService dMakerService;

    @Test
    public void testSomething(){
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(
                        Developer.builder()
                                .developerLevel(DeveloperLevel.SENIOR)
                                .developerSkillType(DeveloperSkillType.FRONT_END)
                                .experienceYears(12)
                                .statusCode(StatusCode.EMPLOYED)
                                .name("olaf")
                                .age(12)
                                .build()

                );

        DeveloperDetailDto developerDetail = dMakerService
                .getDeveloperDetail("memberId");

        assertEquals(DeveloperLevel.SENIOR, developerDetail.getDeveloperLevel());
        assertEquals(DeveloperSkillType.FRONT_END, developerDetail.getDeveloperSkillType());

    }

}
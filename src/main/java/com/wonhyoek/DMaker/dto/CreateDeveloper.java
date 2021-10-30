package com.wonhyoek.DMaker.dto;

import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.type.DeveloperLevel;
import com.wonhyoek.DMaker.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(0)
        @Max(20)
        private Integer experienceYears;

        @NotNull
        @Size(min=3, max = 50, message = "memberId must be 3~50")
        private String memberId;
        @NotNull
        @Size(min=3, max = 20, message = "name must be 3~20")
        private String name;
        @Min(18)
        private Integer age;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experience;

        private String memberId;

        public static Response fromEntity(Developer developer) {
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experience(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }
}

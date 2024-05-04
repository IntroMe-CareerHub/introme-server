package com.introme.talent.dto.response;

import com.introme.talent.entity.Talent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TalentResDTO {
    @NotNull
    private Long id;
    @NotNull
    private String keyword;
    @NotNull
    private String description;
    @NotNull
    private String icon;

    @Builder
    public TalentResDTO(Long id, String keyword, String description, String icon) {
        this.id = id;
        this.keyword = keyword;
        this.description = description;
        this.icon = icon;
    }

    public static TalentResDTO toResponseDTO(Talent talent) {
        return TalentResDTO.builder()
                .id(talent.getId())
                .keyword(talent.getKeyword())
                .description(talent.getDescription())
                .icon(talent.getIcon())
                .build();
    }
}

package com.introme.talent.dto.response;

import com.introme.talent.entity.Talent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TalentResDTO {
    @NonNull
    private Long id;
    @NonNull
    private String keyword;
    @NonNull
    private String description;

    @Builder
    public TalentResDTO(Long id, String keyword, String description) {
        this.id = id;
        this.keyword = keyword;
        this.description = description;
    }

    public static TalentResDTO toResponseDTO(Talent talent) {
        return TalentResDTO.builder()
                .id(talent.getId())
                .keyword(talent.getKeyword())
                .description(talent.getDescription())
                .build();
    }
}

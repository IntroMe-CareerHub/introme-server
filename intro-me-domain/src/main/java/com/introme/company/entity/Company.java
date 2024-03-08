package com.introme.company.entity;

import com.introme.company.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Entity
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column()
    private String image;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column()
    private Map<String, Object> information;

    @Builder
    public Company(Long id, String name, String image, Map<String, Object> information) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
    }

    public static Company toEntity(CompanyDTO companyDTO) {
        return Company.builder()
                .name(companyDTO.getName())
                .image(companyDTO.getImage())
                .information(companyDTO.getInformation())
                .build();
    }
}

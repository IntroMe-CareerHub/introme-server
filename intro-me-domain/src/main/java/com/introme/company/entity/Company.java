package com.introme.company.entity;

import com.introme.company.CompanyInfoJsonConverter;
import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.talent.entity.Talent;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private String image;

    @ColumnDefault("'#EEEEEE'")
    private String backgroundColor;

    @Convert(converter = CompanyInfoJsonConverter.class)
    @Column
    private CompanyInfo companyInfo;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private List<Talent> talents = new ArrayList<>();

    public Company(String name, String image, CompanyInfo companyInfo, Permission permission) {
        this.name = name;
        this.image = image;
        this.companyInfo = companyInfo;
        this.permission = permission;
    }

    @Builder
    public Company(Long id, String name, String image, CompanyInfo companyInfo, Permission permission, List<Talent> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.companyInfo = companyInfo;
        this.permission = permission;
        this.talents = talents;
    }

    public static Company toEntity(CompanyReqDTO companyReqDTO) {
        List<Talent> talentList = companyReqDTO.getTalents().stream()
                .map(Talent::toEntity)
                .toList();

        return Company.builder()
                .name(companyReqDTO.getName())
                .image(companyReqDTO.getImage())
                .companyInfo(companyReqDTO.getCompanyInfo())
                .permission(Permission.APPROVED)
                .talents(talentList)
                .build();
    }

    public static Company toTempEntity(CompanyReqDTO companyReqDTO) {
        List<Talent> talentList = companyReqDTO.getTalents().stream()
                .map(Talent::toEntity)
                .toList();

        return Company.builder()
                .name(companyReqDTO.getName())
                .image(companyReqDTO.getImage())
                .companyInfo(companyReqDTO.getCompanyInfo())
                .permission(Permission.PENDING)
                .talents(talentList)
                .build();
    }
}

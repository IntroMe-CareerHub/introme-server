package com.introme.talent.entity;

import com.introme.company.entity.Company;
import jakarta.persistence.*;

@Entity
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyword;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;
}

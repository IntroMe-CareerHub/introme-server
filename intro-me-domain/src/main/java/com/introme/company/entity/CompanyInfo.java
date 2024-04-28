package com.introme.company.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfo {
    private String location;
    private String url;
    private String recruitUrl;
    private String techBlog;
}

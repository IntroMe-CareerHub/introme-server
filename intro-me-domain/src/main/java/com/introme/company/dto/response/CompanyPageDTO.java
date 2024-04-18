package com.introme.company.dto.response;

import com.introme.company.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyPageDTO<T> {
    private T data;
    private PageInfo pageInfo;
}

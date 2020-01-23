package com.github.abigail830.ThanosContractService.domain.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractField {

    private String name;
    private String type;
    private Integer length;
    private String contractContent;
    private String schemaContent;
}

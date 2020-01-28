package com.github.abigail830.ThanosContractService.domain.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractKey {

    private String name;
    private String version;
    private String consumer;
    private String provider;
}

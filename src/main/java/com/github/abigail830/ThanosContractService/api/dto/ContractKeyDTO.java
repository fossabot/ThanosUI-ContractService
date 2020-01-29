package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractKeyDTO {

    private String id;
    private String name;
    private String version;
    private String consumer;
    private String provider;

    public ContractKeyDTO(Contract contract) {
        this.id = contract.getId();
        this.name = contract.getContractKey().getName();
        this.version = contract.getContractKey().getVersion();
        this.provider = contract.getContractKey().getProvider();
        this.consumer = contract.getContractKey().getConsumer();
    }
}

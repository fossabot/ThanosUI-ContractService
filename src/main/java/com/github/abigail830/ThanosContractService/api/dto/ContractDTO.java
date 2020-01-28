package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import com.github.abigail830.ThanosContractService.domain.contract.ContractField;
import com.github.abigail830.ThanosContractService.domain.contract.ContractKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private String id;
    private String name;
    private String version;
    private String consumer;
    private String provider;
    private String schemaId;

    private LinkedList<ContractField> request;
    private LinkedList<ContractField> response;

    public ContractDTO(Contract contract) {
        this.id = contract.getId();
        this.name = contract.getContractKey().getName();
        this.version = contract.getContractKey().getVersion();
        this.provider = contract.getContractKey().getProvider();
        this.consumer = contract.getContractKey().getConsumer();
        this.schemaId = contract.getSchemaId();
        this.request = contract.getReq();
        this.response = contract.getRes();

    }

    public Contract toContract() {
        return new Contract(
                new ContractKey(name, version, consumer, provider),
                schemaId, request, response);
    }
}

package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import com.github.abigail830.ThanosContractService.domain.contract.ContractField;
import com.github.abigail830.ThanosContractService.domain.contract.ContractKey;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Optional;

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
    private String schemaProvider;
    private String schemaName;
    private String schemaVersion;

    private LinkedList<ContractField> request;
    private LinkedList<ContractField> response;

    public ContractDTO(Contract contract) {
        this.id = contract.getId();
        this.name = contract.getContractKey().getName();
        this.version = contract.getContractKey().getVersion();
        this.provider = contract.getContractKey().getProvider();
        this.consumer = contract.getContractKey().getConsumer();
        this.request = contract.getReq();
        this.response = contract.getRes();

        this.schemaId = contract.getSchemaId();

        this.schemaProvider = Optional.ofNullable(contract.getSchemaKey().getProvider()).orElse("");
        this.schemaName = Optional.ofNullable(contract.getSchemaKey().getName()).orElse("");
        ;
        this.schemaVersion = Optional.ofNullable(contract.getSchemaKey().getVersion()).orElse("");
        ;

    }

    public Contract toContract() {
        return new Contract(id, new ContractKey(name, version, consumer, provider), schemaId,
                new SchemaKey(schemaProvider, schemaName, schemaVersion), request, response);
    }
}

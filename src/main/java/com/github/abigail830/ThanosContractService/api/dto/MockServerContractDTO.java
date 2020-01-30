package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import com.google.common.base.Strings;
import lombok.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MockServerContractDTO {

    private String name;
    private String version;
    private String schemaIndex;
    private String consumer;
    private String provider;

    private LinkedList<MockServerContractFieldDTO> req;
    private LinkedList<MockServerContractFieldDTO> res;

    public MockServerContractDTO(Contract contract) {
        this.name = contract.getContractKey().getName();
        this.version = contract.getContractKey().getVersion();
        this.provider = contract.getContractKey().getProvider();
        this.consumer = contract.getContractKey().getConsumer();
        this.schemaIndex = contract.getSchemaKey().toString();

        this.req = contract.getReq().stream().filter(field -> !Strings.isNullOrEmpty(field.getContractContent()))
                .map(MockServerContractFieldDTO::new).collect(Collectors.toCollection(LinkedList::new));
        this.res = contract.getRes().stream().filter(field -> !Strings.isNullOrEmpty(field.getContractContent()))
                .map(MockServerContractFieldDTO::new).collect(Collectors.toCollection(LinkedList::new));
    }
}

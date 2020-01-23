package com.github.abigail830.ThanosContractService.domain.contract;

import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    private String name;
    private String version;
    private String consumer;
    private String provider;
    private String description;

    private SchemaKey schemaKey;

    private LinkedList<ContractField> req;
    private LinkedList<ContractField> res;
}

package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.contract.ContractField;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MockServerContractFieldDTO {

    private String name;
    private String content;

    public MockServerContractFieldDTO(ContractField contractField) {
        this.name = contractField.getName();
        this.content = contractField.getContractContent();
    }
}

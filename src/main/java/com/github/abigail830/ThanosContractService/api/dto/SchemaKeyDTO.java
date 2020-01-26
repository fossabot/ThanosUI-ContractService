package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchemaKeyDTO {

    String provider;
    String name;
    String version;

    public SchemaKeyDTO(Schema schema) {
        this.provider = schema.getSchemaKey().getProvider();
        this.name = schema.getSchemaKey().getName();
        this.version = schema.getSchemaKey().getVersion();
    }
}

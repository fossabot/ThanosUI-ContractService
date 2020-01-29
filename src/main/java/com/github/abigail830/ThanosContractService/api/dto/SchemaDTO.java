package com.github.abigail830.ThanosContractService.api.dto;

import com.github.abigail830.ThanosContractService.domain.schema.Schema;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaField;
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
public class SchemaDTO {

    String provider;
    String name;
    String version;
    private LinkedList<SchemaField> request;
    private LinkedList<SchemaField> response;

    public SchemaDTO(Schema schema) {
        this.provider = schema.getSchemaKey().getProvider();
        this.name = schema.getSchemaKey().getName();
        this.version = schema.getSchemaKey().getVersion();
        this.request = schema.getRequest();
        this.response = schema.getResponse();
    }

    public Schema toSchema() {
        return new Schema(new SchemaKey(provider, name, version), request, response);
    }
}

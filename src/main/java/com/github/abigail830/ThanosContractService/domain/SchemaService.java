package com.github.abigail830.ThanosContractService.domain;

import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SchemaService {

    @Autowired
    SchemaRepository schemaRepository;

    public List<Schema> getAllSchemas() {
        return schemaRepository.findAll();
    }

    public void addSchema(Schema schema) {
        if (schema.getSchemaKey().isValid()) {
            schemaRepository.insert(schema);
        } else {
            log.warn("Invalid schemaKey for insert [{}]", schema.getSchemaKey());
            throw new BizException(ErrorCode.INVALID_SCHEMA_KEY);
        }
    }

    public Schema updateSchema(Schema schema) {
        if(!schema.isValidForUpdate()){
            log.warn("Invalid schema for update (id or schemaKey is null): [{}]", schema);
            throw new BizException(ErrorCode.INVALID_SCHEMA_FOR_UPDATE);
        }
        final Optional<Schema> bySchemaKey =
                Optional.ofNullable(schemaRepository.findByIdAndSchemaKey(schema.getId(), schema.getSchemaKey()));
        if (bySchemaKey.isPresent()) {
            schemaRepository.save(schema);
            return schema;
        } else {
            log.warn("Schema not exist for update : [{}]", schema);
            throw new BizException(ErrorCode.SCHEMA_NOT_EXIST_FOR_UPDATE);
        }
    }


    public void deleteSchemaById(String id) {
        schemaRepository.deleteById(id);
    }

    public Schema getSchemaBySchemaKey(SchemaKey schemaKey) {
        return schemaRepository.findBySchemaKey(schemaKey);
    }
}

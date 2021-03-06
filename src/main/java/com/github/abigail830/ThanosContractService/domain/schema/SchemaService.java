package com.github.abigail830.ThanosContractService.domain.schema;

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

    public Schema addSchema(Schema schema) {
        if (schema.getSchemaKey().isValid()) {
            return schemaRepository.insert(schema);
        } else {
            log.warn("Invalid schemaKey for insert [{}]", schema.getSchemaKey());
            throw new BizException(ErrorCode.SCHEMA_KEY_INVALID);
        }
    }

    public Schema updateSchema(Schema schema) {
        if(!schema.isValidForUpdate()){
            log.warn("Invalid schema for update (id or schemaKey is null): [{}]", schema);
            throw new BizException(ErrorCode.SCHEMA_INVALID);
        }
        final Optional<Schema> bySchemaKey =
                Optional.ofNullable(schemaRepository.findByIdAndSchemaKey(schema.getId(), schema.getSchemaKey()));
        if (bySchemaKey.isPresent()) {
            schemaRepository.save(schema);
            return schema;
        } else {
            log.warn("Schema not exist for update : [{}]", schema);
            throw new BizException(ErrorCode.SCHEMA_NOT_EXIST);
        }
    }


    public void deleteSchemaById(String id) {
        schemaRepository.deleteById(id);
    }

    public Schema getSchemaBySchemaKey(SchemaKey schemaKey) {
        return schemaRepository.findBySchemaKey(schemaKey);
    }

    public Schema getSchemaById(String id) {
        return schemaRepository.findById(id).orElse(new Schema());
    }

    public List<Schema> getSchemaBySchemaKeys(List<SchemaKey> keys) {
        return schemaRepository.findBySchemaKeyIn(keys);
    }
}

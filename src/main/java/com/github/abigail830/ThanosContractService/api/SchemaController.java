package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.SchemaDTO;
import com.github.abigail830.ThanosContractService.domain.schema.Schema;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaService;
import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/schemas")
public class SchemaController {

    @Autowired
    SchemaService schemaService;

    @PostMapping
    public void addSchema(@RequestBody Schema schema) {
        schemaService.addSchema(schema);
    }

    @PutMapping
    public Schema updateSchema(@RequestBody Schema schema) {
        return schemaService.updateSchema(schema);
    }

    @DeleteMapping("/{id}")
    public void deleteSchema(@PathVariable String id) {
        schemaService.deleteSchemaById(id);
    }

    @GetMapping("/all")
    public List<Schema> getAllSchemas() {
        return schemaService.getAllSchemas();
    }

    @GetMapping
    public SchemaDTO getSchemaBySchemaKey(@RequestParam String index) {
        final SchemaKey schemaKey = convertTokey(index);
        final Schema schema = schemaService.getSchemaBySchemaKey(schemaKey);
        if (schema != null)
            return new SchemaDTO(schema);
        else
            return new SchemaDTO();
    }

    SchemaKey convertTokey(String index) {
        if (index.contains("-")) {
            final String[] split = index.split("-");
            if (split.length == 3) {
                return new SchemaKey(split[0], split[1], split[2]);
            }
        }
        log.warn("Invalid index fail to convert to schemaKey : [{}]", index);
        throw new BizException(ErrorCode.INVALID_SCHEMA_KEY);
    }
}

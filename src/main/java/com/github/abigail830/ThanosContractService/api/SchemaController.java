package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.SchemaDTO;
import com.github.abigail830.ThanosContractService.api.dto.SchemaKeyDTO;
import com.github.abigail830.ThanosContractService.domain.schema.Schema;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaService;
import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public List<SchemaDTO> getAllSchemas() {
        return schemaService.getAllSchemas().stream().map(SchemaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/keys")
    public List<SchemaKeyDTO> getAllSchemaKeys() {
        return schemaService.getAllSchemas().stream().map(SchemaKeyDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/index/{index}")
    public SchemaDTO getSchemaBySchemaKeyAsString(@PathVariable String index) {
        final SchemaKey schemaKey = convertTokey(index);
        final Schema schema = schemaService.getSchemaBySchemaKey(schemaKey);
        if (schema != null)
            return new SchemaDTO(schema);
        else
            return new SchemaDTO();
    }

    @GetMapping("/index")
    public SchemaDTO getSchemaBySchemaKey(@RequestParam String provider,
                                          @RequestParam String name, @RequestParam String version) {
        final SchemaKey schemaKey = new SchemaKey(provider, name, version);
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

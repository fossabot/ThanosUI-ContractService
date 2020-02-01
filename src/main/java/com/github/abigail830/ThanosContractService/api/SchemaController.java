package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.SchemaDTO;
import com.github.abigail830.ThanosContractService.api.dto.SchemaKeyDTO;
import com.github.abigail830.ThanosContractService.domain.schema.Schema;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import com.github.abigail830.ThanosContractService.domain.schema.SchemaService;
import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Add Schemas Detail by id -- Using by ThanosUI")
    @PostMapping
    public SchemaDTO addSchema(@RequestBody SchemaDTO schemaDTO) {
        return new SchemaDTO(schemaService.addSchema(schemaDTO.toSchema()));
    }

    @ApiOperation(value = "Update Schemas Detail by id -- Using by ThanosUI")
    @PutMapping
    public SchemaDTO updateSchema(@RequestBody SchemaDTO schemaDTO) {
        log.info("{}", schemaDTO);
        return new SchemaDTO(schemaService.updateSchema(schemaDTO.toSchema()));
    }

    @ApiOperation(value = "Delete Schemas Detail by id -- Using by ThanosUI")
    @DeleteMapping("/id/{id}")
    public void deleteSchema(@PathVariable String id) {
        schemaService.deleteSchemaById(id);
    }

    @GetMapping
    public List<SchemaDTO> getAllSchemas() {
        return schemaService.getAllSchemas().stream().map(SchemaDTO::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Search Schemas Detail by id -- Using by ThanosUI")
    @GetMapping("/id/{id}")
    public SchemaDTO getSchemaById(@PathVariable String id) {
        return new SchemaDTO(schemaService.getSchemaById(id));
    }

    @GetMapping("/keys/{key}")
    public SchemaDTO getSchemaBySchemaKeyString(@PathVariable String key) {
        final SchemaKey schemaKey = convertTokey(key);
        final Schema schema = schemaService.getSchemaBySchemaKey(schemaKey);
        if (schema != null)
            return new SchemaDTO(schema);
        else
            return new SchemaDTO();
    }

    @ApiOperation(value = "Search Schemas by Key List -- Using by MockServer")
    @GetMapping("/keys")
    public List<SchemaDTO> getSchemaBySchemaKeys(@RequestParam List<String> schemaKeys) {
        final List<SchemaKey> keys = schemaKeys.stream().map(this::convertTokey).collect(Collectors.toList());
        return schemaService.getSchemaBySchemaKeys(keys).stream()
                .map(SchemaDTO::new).collect(Collectors.toList());

    }

    @ApiOperation(value = "Search all Schemas keys -- Using by ThanosUI")
    @GetMapping("/keys/")
    public List<SchemaKeyDTO> getAllSchemaKeys() {
        return schemaService.getAllSchemas().stream().map(SchemaKeyDTO::new).collect(Collectors.toList());
    }

    SchemaKey convertTokey(String index) {
        if (index.contains("-")) {
            final String[] split = index.split("-");
            if (split.length == 3) {
                return new SchemaKey(split[0], split[1], split[2]);
            }
        }
        log.warn("Invalid index fail to convert to schemaKey : [{}]", index);
        throw new BizException(ErrorCode.SCHEMA_KEY_INVALID);
    }
}

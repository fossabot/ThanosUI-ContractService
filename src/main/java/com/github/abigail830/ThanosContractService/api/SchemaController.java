package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.domain.Schema;
import com.github.abigail830.ThanosContractService.domain.SchemaKey;
import com.github.abigail830.ThanosContractService.domain.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schemas")
public class SchemaController {

    @Autowired
    SchemaService schemaService;

    @GetMapping
    public List<Schema> getAllSchemas(){
        return schemaService.getAllSchemas();
    }

    @PostMapping
    public void addSchema(@RequestBody Schema schema){
        schemaService.addSchema(schema);
    }

    @PutMapping
    public Schema updateSchema(@RequestBody Schema schema){
        return schemaService.updateSchema(schema);
    }

    @DeleteMapping("/{id}")
    public void deleteSchema(@PathVariable String id){
        schemaService.deleteSchemaById(id);
    }
}

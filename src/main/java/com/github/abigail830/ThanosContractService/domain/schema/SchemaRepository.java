package com.github.abigail830.ThanosContractService.domain.schema;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemaRepository extends MongoRepository<Schema, String> {

    public List<Schema> findBySchemaKeyProviderAndSchemaKeyName(String provider, String name);

    public Schema findByIdAndSchemaKey(String id, SchemaKey schemaKey);

    public Schema findBySchemaKey(SchemaKey schemaKey);

    public void deleteBySchemaKey(SchemaKey schemaKey);

}

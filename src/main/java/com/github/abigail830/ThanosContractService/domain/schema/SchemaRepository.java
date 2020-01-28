package com.github.abigail830.ThanosContractService.domain.schema;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchemaRepository extends MongoRepository<Schema, String> {

    List<Schema> findBySchemaKeyProviderAndSchemaKeyName(String provider, String name);

    Schema findByIdAndSchemaKey(String id, SchemaKey schemaKey);

    Schema findBySchemaKey(SchemaKey schemaKey);

    Optional<Schema> findById(String id);

    Schema insert(Schema schema);

    void deleteBySchemaKey(SchemaKey schemaKey);

    List<Schema> findBySchemaKeyIn(List<SchemaKey> keys);
}

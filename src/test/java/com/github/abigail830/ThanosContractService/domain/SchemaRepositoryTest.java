package com.github.abigail830.ThanosContractService.domain;

import com.google.common.base.Strings;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Ignore
class SchemaRepositoryTest {

    @Autowired
    SchemaRepository schemaRepository;

    @BeforeEach
    void setUp() {
        schemaRepository.deleteBySchemaKey(new SchemaKey("provider", "name1", "v1"));
        schemaRepository.deleteBySchemaKey(new SchemaKey("provider", "name2", "v1"));
    }

    @AfterEach
    void tearDown() {
        schemaRepository.deleteBySchemaKey(new SchemaKey("provider", "name1", "v1"));
        schemaRepository.deleteBySchemaKey(new SchemaKey("provider", "name2", "v1"));
    }

    @Test
    void findBySchemaKey() {
        final SchemaKey key1 = new SchemaKey("provider", "name1", "v1");
        Schema schema = new Schema(key1, new LinkedList<>(), new LinkedList<>());
        schemaRepository.save(schema);
        assertFalse(Strings.isNullOrEmpty(schemaRepository.findBySchemaKey(key1).getId()));
    }

    @Test
    void findByProviderAndName() {
        final SchemaKey key2 = new SchemaKey("provider", "name2", "v1");
        Schema schema = new Schema(key2, new LinkedList<>(), new LinkedList<>());
        schemaRepository.save(schema);

        final List<Schema> schemas = schemaRepository
                .findBySchemaKeyProviderAndSchemaKeyName("provider", "name2");

        assertEquals(1, schemas.size());
    }
}
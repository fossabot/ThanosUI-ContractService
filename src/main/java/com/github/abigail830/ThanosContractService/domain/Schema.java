package com.github.abigail830.ThanosContractService.domain;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.LinkedList;

@Getter
@ToString
@NoArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(name = "schemaKey_index",
                def = "{'schemaKey.name' : 1, 'schemaKey.version' : -1, 'schemaKey.provider' : 1}", unique = true)
})
public class Schema implements Serializable {

    @Id
    private String id;

    SchemaKey schemaKey;

    private LinkedList<SchemaField> request;
    private LinkedList<SchemaField> response;

    public Schema(SchemaKey schemaKey, LinkedList<SchemaField> request, LinkedList<SchemaField> response) {
        this.schemaKey = schemaKey;
        this.request = request;
        this.response = response;
    }

    public Boolean isValidForUpdate() {
        if (Strings.isNullOrEmpty(id) || !schemaKey.isValid()) {
            return false;
        } else {
            return true;
        }
    }
}

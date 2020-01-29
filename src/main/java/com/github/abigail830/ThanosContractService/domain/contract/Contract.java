package com.github.abigail830.ThanosContractService.domain.contract;

import com.github.abigail830.ThanosContractService.domain.schema.SchemaKey;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(name = "contractKey_index",
                def = "{'contractKey.name' : 1, 'contractKey.version' : -1, " +
                        "'contractKey.provider' : 1,'contractKey.consumer' : 1}", unique = true)
})
public class Contract implements Serializable {

    @Id
    private String id;

    private ContractKey contractKey;
    private String schemaId;
    private SchemaKey schemaKey;

    private LinkedList<ContractField> req;
    private LinkedList<ContractField> res;

    public Contract(ContractKey contractKey, String schemaId, SchemaKey schemaKey,
                    LinkedList<ContractField> req, LinkedList<ContractField> res) {
        this.contractKey = contractKey;
        this.schemaId = schemaId;
        this.schemaKey = schemaKey;
        this.req = req;
        this.res = res;
    }

    public String getIndex() {
        return contractKey.getProvider() + '-' + contractKey.getConsumer();
    }

    public boolean isValidForUpdate() {
        if (Strings.isNullOrEmpty(id) || !contractKey.isValid()) {
            return false;
        } else {
            return true;
        }
    }
}

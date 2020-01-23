package com.github.abigail830.ThanosContractService.domain.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchemaField {

    String name;
    String type;
    Integer length;
    String content;

}

package com.github.abigail830.ThanosContractService.domain;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SchemaKey {
    String provider;
    String name;
    String version;

    Boolean isValid() {
        if (Strings.isNullOrEmpty(provider) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(version)) {
            return false;
        } else {
            return true;
        }
    }


}

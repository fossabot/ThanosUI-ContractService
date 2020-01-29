package com.github.abigail830.ThanosContractService.domain.contract;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractKey {

    private String name;
    private String version;
    private String consumer;
    private String provider;

    public boolean isValid() {
        if (Strings.isNullOrEmpty(provider) || Strings.isNullOrEmpty(consumer)
                || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(version)) {
            return false;
        } else {
            return true;
        }
    }
}

package com.github.abigail830.ThanosContractService.domain;

import com.google.common.base.Strings;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchemaKey {
    String provider;
    String name;
    String version;

    Boolean isValid(){
        if(Strings.isNullOrEmpty(provider) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(version)){
            return false;
        }else{
            return true;
        }
    }
}

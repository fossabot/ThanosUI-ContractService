package com.github.abigail830.ThanosContractService.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TcpRequestDTO {
    String host;
    String port;
    String requestMsg;
}

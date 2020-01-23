package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.TcpRequestDTO;
import com.github.abigail830.ThanosContractService.api.dto.TcpResponseDTO;
import com.github.abigail830.ThanosContractService.appl.UtilApplService;
import com.github.abigail830.ThanosContractService.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class UtilController {

    @Autowired
    private UtilApplService utilApplService;

    @PostMapping("/tcp")
    public TcpResponseDTO sendTcpRequest(@RequestBody TcpRequestDTO tcpRequestDTO) {
        try {
            return new TcpResponseDTO(utilApplService.sendTcpMsg(
                    tcpRequestDTO.getHost(),
                    Integer.valueOf(tcpRequestDTO.getPort()),
                    tcpRequestDTO.getRequestMsg()));

        } catch (NumberFormatException ex) {
            throw new BizException("", ex);
        }
    }


}

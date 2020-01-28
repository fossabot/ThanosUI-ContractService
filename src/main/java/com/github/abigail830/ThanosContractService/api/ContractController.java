package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.ContractDTO;
import com.github.abigail830.ThanosContractService.domain.contract.ContractService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @ApiOperation(value = "Add Contract Detail-- Using by ThanosUI")
    @PostMapping
    public void addContract(@RequestBody ContractDTO contractDTO) {
        log.info("{}", contractDTO);
        contractService.addContract(contractDTO.toContract());
    }

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        return contractService.getAllContracts().stream()
                .map(ContractDTO::new).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteContractById(String id) {
        contractService.deleteContractByid(id);
    }
}

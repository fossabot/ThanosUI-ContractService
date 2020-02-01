package com.github.abigail830.ThanosContractService.api;

import com.github.abigail830.ThanosContractService.api.dto.ContractDTO;
import com.github.abigail830.ThanosContractService.api.dto.ContractKeyDTO;
import com.github.abigail830.ThanosContractService.api.dto.MockServerContractDTO;
import com.github.abigail830.ThanosContractService.appl.GenerateTestCaseApplService;
import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import com.github.abigail830.ThanosContractService.domain.contract.ContractService;
import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    GenerateTestCaseApplService generateTestCaseApplService;

    @PostMapping
    public void addContract(@RequestBody ContractDTO contractDTO) {
        contractService.addContract(contractDTO.toContract());
    }

    @PutMapping
    public void updateContract(@RequestBody ContractDTO contractDTO) {
        log.info("{}", contractDTO);
        contractService.updateContract(contractDTO.toContract());
    }

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        return contractService.getAllContracts().stream()
                .map(ContractDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/domain")
    public List<Contract> getAllPureContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/keys")
    public List<ContractKeyDTO> getAllContractKeys() {
        return contractService.getAllContracts().stream()
                .map(ContractKeyDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/schemaId/{schemaId}")
    public List<ContractDTO> getContractsBySchemaId(@PathVariable String schemaId) {
        return contractService.getContractBySchemaId(schemaId).stream()
                .map(ContractDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/count/schemaId/{schemaId}")
    public Integer getContractsCountBySchemaId(@PathVariable String schemaId) {
        return contractService.getContractBySchemaId(schemaId).size();
    }

    @GetMapping("/id/{id}")
    public ContractDTO getContractsById(@PathVariable String id) {
        final Optional<Contract> contractById = contractService.getContractById(id);
        return contractById.map(ContractDTO::new)
                .orElseThrow(() -> new BizException(ErrorCode.CONTRACT_NOT_FOUND));
    }

    @DeleteMapping("/id/{id}")
    public void deleteContractById(@PathVariable String id) {
        contractService.deleteContractByid(id);
    }

    @ApiOperation(value = "Search Contracts by index (provider-consumer) -- Using by MockServer")
    @GetMapping("/index/{index}")
    public List<MockServerContractDTO> getAllContractByIndex(@PathVariable String index) {
        final int i = index.indexOf("-");
        if ((i != -1) && (i < index.length())) {
            String provider = index.substring(0, i);
            String consumer = index.substring(i + 1);
            return contractService.getAllContractsByIndex(provider, consumer).stream()
                    .map(MockServerContractDTO::new).collect(Collectors.toList());
        } else {
            throw new BizException(ErrorCode.CONTRACT_INDEX_INVALID);
        }
    }

    @ApiOperation(value = "Search Contracts count by index (provider-consumer) -- Using by MockServer")
    @GetMapping("/count/index/{index}")
    public Integer getAllContractCountByIndex(@PathVariable String index) {
        final int i = index.indexOf("-");
        if ((i != -1) && (i < index.length())) {
            String provider = index.substring(0, i);
            String consumer = index.substring(i + 1);
            return contractService.getAllContractsByIndex(provider, consumer).size();
        } else {
            throw new BizException(ErrorCode.CONTRACT_INDEX_INVALID);
        }
    }

    @ApiOperation(value = "Search all indexs (provider-consumer) -- Using by MockServer")
    @GetMapping("/index")
    public List<String> getAllContractIndexs() {
        return contractService.getAllContracts().stream()
                .map(Contract::getIndex).distinct().collect(Collectors.toList());
    }

    @GetMapping("/generate-junit/id/{contractId}")
    public String generateJunitTestCase(@PathVariable String contractId) {

        return generateTestCaseApplService.generateJunit(contractId);
    }
}

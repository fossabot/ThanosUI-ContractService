package com.github.abigail830.ThanosContractService.domain.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    public void addContract(Contract contract) {
        final Contract insert = contractRepository.insert(contract);
        log.info("{}", insert);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public void deleteContractByid(String id) {
        contractRepository.deleteById(id);
    }

    public List<Contract> getContractBySchemaId(String schemaId) {
        return contractRepository.findBySchemaId(schemaId);
    }

    public Optional<Contract> getContractById(String id) {
        return contractRepository.findById(id);
    }
}

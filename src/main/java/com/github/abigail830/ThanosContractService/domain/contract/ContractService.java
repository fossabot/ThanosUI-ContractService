package com.github.abigail830.ThanosContractService.domain.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

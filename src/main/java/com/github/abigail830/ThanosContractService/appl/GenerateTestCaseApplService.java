package com.github.abigail830.ThanosContractService.appl;

import com.github.abigail830.ThanosContractService.domain.contract.Contract;
import com.github.abigail830.ThanosContractService.domain.contract.ContractRepository;
import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
import com.github.abigail830.ThanosContractService.infrastructure.JsonUtil;
import com.thanos.contract.codegenerator.appl.JarApplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerateTestCaseApplService {

    private ContractRepository contractRepository;
    private JarApplService jarApplService;

    @Autowired
    public GenerateTestCaseApplService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
        this.jarApplService = new JarApplService();
    }

    public String generateJunit(String contractId) {
        final Optional<Contract> contract = contractRepository.findById(contractId);
        if (contract.isPresent()) {
            final String contractJson = JsonUtil.toJson(contract.get());
            return jarApplService.generate(contractJson);
        } else {
            throw new BizException(ErrorCode.CONTRACT_NOT_FOUND);
        }
    }
}

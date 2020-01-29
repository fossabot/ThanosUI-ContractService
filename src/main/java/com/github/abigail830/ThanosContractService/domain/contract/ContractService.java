package com.github.abigail830.ThanosContractService.domain.contract;

import com.github.abigail830.ThanosContractService.exception.BizException;
import com.github.abigail830.ThanosContractService.exception.ErrorCode;
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

    public void updateContract(Contract contract) {
        if (!contract.isValidForUpdate()) {
            log.warn("Invalid schema for update (id or schemaKey is null): [{}]", contract);
            throw new BizException(ErrorCode.INVALID_CONTRACT_FOR_UPDATE);
        }
        final Optional<Contract> contractOptional =
                contractRepository.findByIdAndContractKey(contract.getId(), contract.getContractKey());

        if (contractOptional.isPresent()) {
            contractRepository.save(contract);
        } else {
            log.warn("Schema not exist for update : [{}]", contract);
            throw new BizException(ErrorCode.CONTRACT_NOT_EXIST_FOR_UPDATE);
        }
    }
}

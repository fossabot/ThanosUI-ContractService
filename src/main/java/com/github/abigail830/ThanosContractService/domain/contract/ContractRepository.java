package com.github.abigail830.ThanosContractService.domain.contract;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {

    List<Contract> findBySchemaId(String schemaId);

    Optional<Contract> findById(String id);

    Contract insert(Contract contract);

    void deleteByContractKey(ContractKey contractKey);

    void deleteById(String id);

    List<Contract> findByContractKeyIn(List<ContractKey> contractKeys);

}

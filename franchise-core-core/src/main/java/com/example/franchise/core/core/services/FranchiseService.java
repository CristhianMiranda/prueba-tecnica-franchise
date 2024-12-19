package com.example.franchise.core.core.services;

import com.example.franchise.core.common.constants.ErrorMessages;
import com.example.franchise.core.core.components.I18NComponent;
import com.example.franchise.core.core.exceptions.ApiException;
import com.example.franchise.core.domain.model.BranchRecord;
import com.example.franchise.core.domain.model.FranchiseRecord;
import com.example.franchise.core.domain.repository.FranchiseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FranchiseService {
    private final I18NComponent i18NService;
    private final FranchiseRepository repository;
    private final BranchService branchService;

    public FranchiseService(I18NComponent i18NService, FranchiseRepository repository, BranchService branchService) {
        this.i18NService = i18NService;
        this.repository = repository;
        this.branchService = branchService;
    }

    @Transactional(rollbackOn = ApiException.class)
    public FranchiseRecord create(String name) {
        log.info("FranchiseService::create --name:[{}]",name);
        if(repository.existsByName(name)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_FRANCHISE_EXIST_NAME));
        }
        FranchiseRecord entity = new FranchiseRecord();
        entity.setName(name);
        return repository.save(entity);
    }

    @Transactional(rollbackOn = ApiException.class)
    public FranchiseRecord addBranch(Long franchiseId, Long branchId)  {
        log.info("FranchiseService::addBranch --franchiseId:[{}], --branchId:[{}]",franchiseId,branchId);
        FranchiseRecord franchiseRecord = getById(franchiseId);
        BranchRecord branchEntityToAdd = branchService.getById(branchId);
        franchiseRecord.getBranches().add(branchEntityToAdd);
        return repository.save(franchiseRecord);
    }

    @Transactional(rollbackOn = ApiException.class)
    public FranchiseRecord updateName (Long id, String requestName)  {
        log.info("FranchiseService::updateName --id:[{}], --requestName:[{}]",id,requestName);
        if(repository.existsByNameAndIdNot(requestName,id)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_UPDATE_FRANCHISE_EXIST_NAME));
        }
        FranchiseRecord entity = getById(id);
        entity.setName(requestName);
        return repository.save(entity);
    }

    public FranchiseRecord getById(Long id) throws ApiException {
        log.info("FranchiseService::getById --id:[{}]",id);
        return repository.findById(id).orElseThrow(()-> new ApiException(i18NService.getMessage(ErrorMessages.ERROR_FRANCHISE_NOT_FOUND)));
    }
}

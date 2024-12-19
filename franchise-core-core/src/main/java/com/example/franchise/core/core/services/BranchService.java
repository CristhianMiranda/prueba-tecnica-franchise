package com.example.franchise.core.core.services;

import com.example.franchise.core.common.constants.ErrorMessages;
import com.example.franchise.core.core.components.I18NComponent;
import com.example.franchise.core.core.exceptions.ApiException;
import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.BranchRecord;
import com.example.franchise.core.domain.model.FranchiseRecord;
import com.example.franchise.core.domain.model.ProductRecord;
import com.example.franchise.core.domain.repository.BranchRepository;
import com.example.franchise.core.domain.repository.FranchiseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BranchService {
    private final I18NComponent i18NService;
    private final BranchRepository repository;
    private final ProductService productService;
    private final FranchiseRepository franchiseRepository;

    public BranchService(I18NComponent i18NService, BranchRepository repository, ProductService productService, FranchiseRepository franchiseRepository) {
        this.i18NService = i18NService;
        this.repository = repository;
        this.productService = productService;
        this.franchiseRepository = franchiseRepository;
    }

    @Transactional(rollbackOn = ApiException.class)
    public BranchRecord create(String name, Long franchiseId)  {
        log.info("BranchService::create --name:[{}], --franchiseId:[{}]",name, franchiseId);
        if(repository.existsByName(name)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_BRANCH_EXIST_NAME));
        }
        BranchRecord entity = new BranchRecord();
        entity.setName(name);
        entity.setProducts(new HashSet<>());
        FranchiseRecord franchiseRecord = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new ApiException(i18NService.getMessage(ErrorMessages.ERROR_FRANCHISE_NOT_FOUND)));
        entity.setFranchise(franchiseRecord);
        return repository.save(entity);
    }

    @Transactional(rollbackOn = ApiException.class)
    public BranchRecord updateName (Long id, String requestName)  {
        log.info("BranchService::updateName --id:[{}], --requestName:[{}]",id,requestName);
        if(repository.existsByNameAndIdNot(requestName,id)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_UPDATE_BRANCH_EXIST_NAME));
        }
        BranchRecord entity = getById(id);
        entity.setName(requestName);
        return repository.save(entity);
    }

    public BranchRecord getById(Long id)  {
        log.info("BranchService::getById --id:[{}]", id);
        return repository.findById(id).orElseThrow(()-> new ApiException(i18NService.getMessage(ErrorMessages.ERROR_BRANCH_NOT_FOUND)));
    }

}

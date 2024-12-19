package com.example.franchise.core.app.rest;

import com.example.franchise.core.core.services.BranchService;
import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.BranchRecord;
import com.example.franchise.core.domain.model.FranchiseRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.franchise.core.common.router.Router.BranchAPI.*;

@Slf4j
@RestController
@RequestMapping(ROOT)
public class BranchController {
    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BranchRecord create(@RequestParam String name, @RequestParam Long franchiseId) {
        log.info("BranchController::create --name[{}], --franchiseId:[{}]",name, franchiseId);
        return service.create(name, franchiseId);
    }

    @PutMapping(NAME)
    @ResponseStatus(HttpStatus.OK)
    public BranchRecord updateName(@RequestParam Long id, @RequestParam String requestName) {
        log.info("BranchController::updateName --id[{}], --requestName[{}]",id, requestName);
        return service.updateName(id, requestName);
    }

}

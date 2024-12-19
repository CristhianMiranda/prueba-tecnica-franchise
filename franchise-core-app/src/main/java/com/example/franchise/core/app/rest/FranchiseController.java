package com.example.franchise.core.app.rest;

import com.example.franchise.core.core.services.FranchiseService;
import com.example.franchise.core.domain.model.FranchiseRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.OK;
import static com.example.franchise.core.common.router.Router.FranchiseAPI.*;

@Slf4j
@RestController
@RequestMapping(ROOT)
public class FranchiseController {
    private final FranchiseService service;

    public FranchiseController(FranchiseService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(OK)
    public FranchiseRecord create(@RequestParam String name){
        log.info("FranchiseController::create --name[{}]",name);
        return service.create(name);
    }

    @PutMapping(NAME)
    @ResponseStatus(OK)
    public FranchiseRecord updateName(@RequestParam Long id, @RequestParam String requestName)  {
        log.info("FranchiseController::updateName --id[{}], --requestName[{}]",id, requestName);
        return service.updateName(id, requestName);
    }
}

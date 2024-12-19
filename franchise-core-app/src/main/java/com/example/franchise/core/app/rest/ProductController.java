package com.example.franchise.core.app.rest;

import com.example.franchise.core.common.responses.ProductDto;
import com.example.franchise.core.core.services.ProductService;
import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.ProductRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.franchise.core.common.router.Router.ProductAPI.*;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping(ROOT)
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ProductDto create(@RequestParam String name, @RequestParam int stock, @RequestParam Long branchId) {
        log.info("ProductController::create --name[{}], --stock:[{}], --branchId:[{}]",name, stock, branchId);
        return service.create(name, stock, branchId);
    }


    @GetMapping(TOP)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductTopDto> getAllTopByFranchiseId(@PathVariable Long franchiseId){
        log.info("BranchController::getAllTopByFranchiseId --franchiseId[{}]",franchiseId);
        return service.getAllTopByFranchiseId(franchiseId);
    }

    @PutMapping(NAME)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateName(@RequestParam Long id, @RequestParam String requestName) {
        log.info("ProductController::updateName --id[{}], --requestName[{}]",id, requestName);
        return service.updateName(id, requestName);
    }

    @PutMapping(STOCK)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateStock(@RequestParam Long id, @RequestParam int requestStock) {
        log.info("ProductController::updateName --id[{}], --requestStock[{}]",id, requestStock);
        return service.updateStock(id, requestStock);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        log.info("BranchController::deleteProduct --id[{}]",id);
        service.delete(id);
    }
}

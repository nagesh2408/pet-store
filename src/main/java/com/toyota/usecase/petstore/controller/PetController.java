package com.toyota.usecase.petstore.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toyota.usecase.petstore.repository.PetEntity;
import com.toyota.usecase.petstore.service.PetService;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets")
    public List<PetEntity> getPets() {
        return petService.get();
    }

    @GetMapping("/pets/{petid}")
    public PetEntity getPets(@PathVariable("petid") @NotNull final Long id) {
        return petService.get(id);
    }

    @PostMapping("/pet")
    public void save(@RequestBody @NotNull PetEntity petEntity) {
        petService.save(petEntity);
    }

    @DeleteMapping("/pets/{petid}")
    public void delete(@PathVariable("petid") @NotNull final Long id) {
        petService.delete(id);
    }

}

package com.toyota.usecase.petstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyota.usecase.petstore.repository.PetEntity;
import com.toyota.usecase.petstore.repository.PetRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<PetEntity> get() {
        log.info("Request to get all the avaliable pets");
        return repository.findAll();
    }

    public PetEntity get(final Long id) {
        if (id == null) {
            log.error("Pet id is required to get the pet");
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    public void save(final PetEntity pet) {
        if (pet == null) {
            log.warn("pet object is null");
            return;
        }
        log.info("saving pet information", pet);
        repository.save(pet);
    }

    public void delete(final Long id) {
        if (id == null || !repository.findById(id).isPresent()) {
            log.error("Pet id is required to delete the pet");
            return;
        }
        repository.deleteById(id);
    }
}

package com.toyota.usecase.petstore.service;

import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.toyota.usecase.petstore.repository.PetEntity;
import com.toyota.usecase.petstore.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    private static final Long PET_ID = Long.valueOf(1);

    @Mock
    private PetRepository repository;

    @InjectMocks
    private PetService petService;

    @Captor
    private ArgumentCaptor<PetEntity> petEntityArgumentCaptor;

    private PetEntity petEntity;

    @BeforeEach
    public void setup() {
        petEntity = new PetEntity();
        petEntity.setId(PET_ID);
        petEntity.setName("Dog");
        petEntity.setPrice(BigDecimal.TEN);
    }

    @Test
    public void testGet() {
        when(repository.findAll()).thenReturn(singletonList(petEntity));

        assertFalse(petService.get().isEmpty());

        verify(repository).findAll();
    }

    @Test
    public void testGetByIdWithMissingId() {
        assertNull(petService.get(null));

        verify(repository, never()).findById(PET_ID);
    }

    @Test
    public void testGetByIdWithNoPetAvailable() {
        when(repository.findById(PET_ID)).thenReturn(empty());

        assertNull(petService.get(PET_ID));

        verify(repository).findById(PET_ID);
    }

    @Test
    public void testGetById() {
        when(repository.findById(PET_ID)).thenReturn(of(petEntity));

        assertNotNull(petService.get(PET_ID));

        verify(repository).findById(PET_ID);
    }

    @Test
    public void testSave() {
        petService.save(petEntity);

        verify(repository).save(petEntityArgumentCaptor.capture());

        assertEquals(petEntity, petEntityArgumentCaptor.getValue());
    }

    @Test
    public void testDeleteWithNoExistingEntity() {
        when(repository.findById(PET_ID)).thenReturn(empty());

        petService.delete(PET_ID);

        verify(repository, never()).deleteById(PET_ID);
    }

    @Test
    public void testDelete() {
        when(repository.findById(PET_ID)).thenReturn(of(petEntity));

        petService.delete(PET_ID);

        verify(repository).deleteById(PET_ID);
    }
}

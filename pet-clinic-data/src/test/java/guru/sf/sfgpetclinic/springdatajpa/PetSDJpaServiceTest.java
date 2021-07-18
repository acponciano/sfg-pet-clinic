package guru.sf.sfgpetclinic.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.sf.sfgpetclinic.model.Owner;
import guru.sf.sfgpetclinic.model.Pet;
import guru.sf.sfgpetclinic.repositories.OwnerRepository;
import guru.sf.sfgpetclinic.repositories.PetRepository;
import guru.sf.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    public static final String PET_NAME = "DJulie";
    public static final String OWNER_LAST_NAME = "Ponciano";

    @Mock
    PetRepository petRepository;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnPet;
    Owner petOwner;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).name(PET_NAME).build();
        petOwner = Owner.builder().id(3L).lastName(OWNER_LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(petRepository.findById(any())).thenReturn(Optional.of(returnPet));

        Pet djulie = service.findById(1L);

        assertEquals(PET_NAME, djulie.getName());

        verify(petRepository).findById(any());
    }

    @Test
    void findAll() {
        Set<Pet> returnPetsSet = new HashSet<>();
        returnPetsSet.add(Pet.builder().id(1l).build());
        returnPetsSet.add(Pet.builder().id(2l).build());

        when(petRepository.findAll()).thenReturn(returnPetsSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(1L);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        petOwner.getPets().add(petToSave);
        Pet savedPet = service.save(petToSave);

        assertNotNull(savedPet);

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);

        // default is 1 times
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(petRepository).deleteById(anyLong());
    }
}
package guru.sf.sfgpetclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import guru.sf.sfgpetclinic.repositories.OwnerRepository;
import guru.sf.sfgpetclinic.repositories.PetRepository;
import guru.sf.sfgpetclinic.repositories.PetTypeRepository;
import guru.sf.sfgpetclinic.springdatajpa.OwnerSDJpaService;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

	private static final String LAST_NAME1 = "lastName1";
	private static final String LAST_NAME2 = "2emaNtsal";
	private static final Long OWNER_ID1 = 1L;
	private static final Long OWNER_ID2 = 2L;

	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetRepository petRepository;
	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJpaService service;

	Owner returnOwner;
	Set<Owner> returnOwnerSet;

	@BeforeEach
	public void setup() {
		this.service = new OwnerSDJpaService(ownerRepository, petRepository, petTypeRepository);

		returnOwner = Owner.builder().id(OWNER_ID1).lastName(LAST_NAME1).build();

		returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(OWNER_ID1).lastName(LAST_NAME1).build());
		returnOwnerSet.add(Owner.builder().id(OWNER_ID2).lastName(LAST_NAME2).build());

	}

	@Test
	public void shouldFindByLastName() {

		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

		Owner actualValue = service.findByLastName(LAST_NAME1);
		assertEquals(LAST_NAME1, actualValue.getLastName());
		assertEquals(OWNER_ID1, actualValue.getId());
	}

	@Test
	public void shouldFindAll() {

		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

		Set<Owner> actualValue = service.findAll();

		assertNotNull(actualValue);
		assertEquals(2, actualValue.size());
	}

	@Test
	public void shouldFindById() {

		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

		Owner actualValue = service.findById(OWNER_ID1);

		assertNotNull(actualValue);
		assertEquals(LAST_NAME1, actualValue.getLastName());
		assertEquals(OWNER_ID1, actualValue.getId());
	}

	@Test
	public void shouldNotFindById() {

		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

		Owner actualValue = service.findById(OWNER_ID1);

		assertNull(actualValue);
	}

	@Test
	public void shouldSave() {

		when(ownerRepository.save(any())).thenReturn(returnOwner);

		Owner actualValue = service.save(returnOwner);

		assertNotNull(actualValue);
		assertEquals(LAST_NAME1, actualValue.getLastName());
		assertEquals(OWNER_ID1, actualValue.getId());
	}

	@Test
	public void shouldDelete() {
		service.delete(returnOwner);

		verify(ownerRepository).delete(any());
	}

	@Test
	public void shouldDeleteById() {
		service.deleteById(OWNER_ID1);

		verify(ownerRepository).deleteById(anyLong());
	}

}

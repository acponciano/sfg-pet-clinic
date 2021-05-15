package guru.sf.sfgpetclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import guru.sf.sfgpetclinic.model.Owner;
import guru.sf.sfgpetclinic.services.map.OwnerMapService;
import guru.sf.sfgpetclinic.services.map.PetMapService;
import guru.sf.sfgpetclinic.services.map.PetTypeMapService;

@ExtendWith(SpringExtension.class)
public class OwnerMapServiceTest {

	OwnerMapService ownerMapService;

	final Long onwerId = 1L;

	@BeforeEach
	public void setup() {
		this.ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

		ownerMapService.save(Owner.builder().id(onwerId).build());
	}

	@Test
	public void shouldFindAll() {
		Set<Owner> actualValue = ownerMapService.findAll();

		assertEquals(1, actualValue.size());
	}

	@Test
	public void shouldFindById() {
		// TODO: initialize args
		Long id = null;

		Owner actualValue = ownerMapService.findById(id);

		// TODO: assert scenario
	}

	@Test
	public void shouldSave() {
		// TODO: initialize args
		Owner object = null;

		Owner actualValue = ownerMapService.save(object);

		// TODO: assert scenario
	}

	@Test
	public void shouldDelete() {
		// TODO: initialize args
		Owner object = null;

		ownerMapService.delete(object);

		// TODO: assert scenario
	}

	@Test
	public void shouldDeleteById() {
		// TODO: initialize args
		Long id = null;

		ownerMapService.deleteById(id);

		// TODO: assert scenario
	}

	@Test
	public void shouldFindByLastName() {
		// TODO: initialize args
		String lastName = null;

		Owner actualValue = ownerMapService.findByLastName(lastName);

		// TODO: assert scenario
	}
}

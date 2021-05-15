package guru.sf.sfgpetclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

	final Long ownerId1 = 1L;
	final Long ownerId2 = 1L;
	final String lastName1 = "lastName1";

	@BeforeEach
	public void setup() {
		this.ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

		ownerMapService.save(Owner.builder().id(ownerId1).lastName(lastName1).build());
	}

	@Test
	public void shouldFindAll() {
		Set<Owner> actualValue = ownerMapService.findAll();

		assertEquals(1, actualValue.size());
	}

	@Test
	public void shouldFindById() {

		Owner actualValue = ownerMapService.findById(ownerId1);

		assertEquals(1, actualValue.getId());

	}

	@Test
	public void shouldSaveExistingId() {
		Owner object = new Owner();
		object.setId(ownerId2);
		object.setAddress("address");
		object.setCity("city");
		object.setFirstName("firstName");
		object.setLastName("lastName");
		object.setTelephone("telephone");

		Owner actualValue = ownerMapService.save(object);

		assertNotNull(actualValue);
		assertEquals(ownerId2, actualValue.getId());
		assertEquals("address", actualValue.getAddress());
		assertEquals("city", actualValue.getCity());
		assertEquals("firstName", actualValue.getFirstName());
		assertEquals("lastName", actualValue.getLastName());
		assertEquals("telephone", actualValue.getTelephone());

	}

	@Test
	public void shouldSaveNoId() {
		Owner object = new Owner();
		object.setAddress("address");
		object.setCity("city");
		object.setFirstName("firstName");
		object.setLastName("lastName");
		object.setTelephone("telephone");

		Owner actualValue = ownerMapService.save(object);

		assertNotNull(actualValue);
		assertNotNull(actualValue.getId());
		assertEquals("address", actualValue.getAddress());
		assertEquals("city", actualValue.getCity());
		assertEquals("firstName", actualValue.getFirstName());
		assertEquals("lastName", actualValue.getLastName());
		assertEquals("telephone", actualValue.getTelephone());

	}

	@Test
	public void shouldDelete() {
		ownerMapService.delete(ownerMapService.findById(ownerId1));

		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void shouldDeleteById() {
		ownerMapService.deleteById(ownerId1);

		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void shouldFindByLastName() {
		Owner actualValue = ownerMapService.findByLastName(lastName1);
		assertEquals(lastName1, actualValue.getLastName());
		assertEquals(ownerId1, actualValue.getId());
	}

	@Test
	public void shouldFindByLastNameNotFound() {
		Owner actualValue = ownerMapService.findByLastName("foo");
		assertNull(actualValue);
	}
}

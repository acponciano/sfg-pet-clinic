package guru.springframework.sfgpetclinic.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.sfgpetclinic.model.Owner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryIT {

    @Autowired
    OwnerRepository ownerRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFindByLastName() throws Exception {

        Owner owner = ownerRepository.findByLastName("Glenanne");

        assertEquals("Glenanne", owner.getLastName());
    }

}

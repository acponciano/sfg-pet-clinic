package guru.sf.sfgpetclinic.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import guru.sf.sfgpetclinic.model.Owner;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OwnerRepositoryIT {

    @Autowired
    OwnerRepository ownerRepository;

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testFindByLastName() throws Exception {

        Owner owner = ownerRepository.findByLastName("Database script");

        assertEquals("Database script", owner.getLastName());
    }

}

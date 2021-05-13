package guru.springframework.sfgpetclinic.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgpetclinic.model.Specialty;

public class SpecialtyTest {

    Specialty specialty;

    @Before
    public void setUp() {
        specialty = new Specialty();
    }

    @Test
    public void testGetId() {
        Long idValue = 4L;
        specialty.setId(idValue);
        assertEquals(idValue, specialty.getId());

    }

}

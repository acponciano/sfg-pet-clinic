package guru.sf.sfgpetclinic.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.sf.sfgpetclinic.model.Specialty;

public class SpecialtyTest {

    Specialty specialty;

    @BeforeEach
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

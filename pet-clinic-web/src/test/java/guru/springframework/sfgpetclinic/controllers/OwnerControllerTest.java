package guru.springframework.sfgpetclinic.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.sfgpetclinic.services.OwnerService;

public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    OwnerController ownerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ownerController = new OwnerController(ownerService);

    }

    @Test
    public void testName() {
        String viewName = ownerController.listOwners(model);

        assertEquals(viewName, "owners/index");
        verify(ownerService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("owners"), anySet());
    }

}

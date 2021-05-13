package guru.springframework.sfgpetclinic.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.ui.Model;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    OwnerController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new OwnerController(ownerService);

    }

    @Test
    public void testViewName() throws Exception {

        String viewName = controller.listOwners(model);

        assertEquals(viewName, "owners/index");
        verify(ownerService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("owners"), anySet());
    }

    @Test
    public void testOwnersList() throws Exception {

        // Given

        Set<Owner> owners = new HashSet<>();
        owners.add(new Owner());

        Owner owner = new Owner();
        owner.setId(1L);
        owners.add(owner);

        when(ownerService.findAll()).thenReturn(owners);

        ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // When
        String viewName = controller.listOwners(model);

        // Then
        assertEquals(viewName, "owners/index");
        verify(ownerService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());

        Set<Owner> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }

    @Test
    public void testmockMVC() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/owners/index.html")).andExpect(view().name("owners/index")).andExpect(status().isOk());

    }
}

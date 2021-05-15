package guru.sf.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.sf.sfgpetclinic.controllers.OwnerController;
import guru.sf.sfgpetclinic.model.Owner;
import guru.sf.sfgpetclinic.services.OwnerService;

public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    OwnerController controller;

    @BeforeEach
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
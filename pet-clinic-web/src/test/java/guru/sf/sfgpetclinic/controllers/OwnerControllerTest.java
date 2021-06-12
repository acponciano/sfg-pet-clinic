package guru.sf.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.sf.sfgpetclinic.model.Owner;
import guru.sf.sfgpetclinic.services.OwnerService;

@ExtendWith(SpringExtension.class)
public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).lastName("LastName1").build());
        owners.add(Owner.builder().id(2L).lastName("LastName2").build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testmockMVCIndexUrl() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/owners/index.html")).andExpect(view().name("owners/index")).andExpect(status().isOk());

    }

    @Test
    public void shouldListOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        verify(ownerService, times(1)).findAll();
    }

    @Test
    public void shouldFindOwners() throws Exception {

        mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("notImplemented"));

        verifyNoInteractions(ownerService);

    }

    @Test
    public void shouldProcessoFindFormReturnMany() throws Exception {

        /*
         * TODO Implement controller method
         * when(ownerService.findAllByLastName(anyString())).thenReturn(owners);
         * 
         * mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(
         * view().name("owners/ownerList")) .andExpect(model().attribute("owners",
         * hasSize(2)));
         * 
         * verify(ownerService, times(1)).findAllByLastName(anyString());
         */
    }

    @Test
    public void shouldShowOwner() throws Exception {

        mockMvc.perform(get("/owners/1/show")).andExpect(status().isOk()).andExpect(view().name("owners/1/show"));

        verify(ownerService, times(1)).findById(1L);

    }

    @Test
    public void shouldDisplayOwner() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/123")).andExpect(status().isOk()).andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

        verify(ownerService, times(1)).findById(123L);

    }
}

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    Set<Owner> ownersSet;
    List<Owner> ownersList;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        ownersSet = new HashSet<>();
        ownersSet.add(Owner.builder().id(1L).lastName("LastName1").build());
        ownersSet.add(Owner.builder().id(2L).lastName("LastName2").build());

        ownersList = new ArrayList<>();
        ownersList.add(Owner.builder().id(1L).lastName("LastName1").build());
        ownersList.add(Owner.builder().id(2L).lastName("LastName2").build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testmockMVCIndexUrl() throws Exception {
        System.out.println("Owner Controller Tes - testmockMVCIndexUrl");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/owners")).andExpect(view().name("owners/findOwners")).andExpect(status().isOk());

    }

    @Test
    public void shouldListOwners() throws Exception {
        System.out.println("Owner Controller Tes - shouldListOwners");
        when(ownerService.findAllByLastNameLike("")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"));

        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    public void shouldFindOwners() throws Exception {

        mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"));

        verifyNoInteractions(ownerService);

    }

    @Test
    public void shouldProcessFindFormReturnMany() throws Exception {
        System.out.println("Owner Controller Tes - shouldProcessFindFormReturnMany");

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownersList);

        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));

        verify(ownerService, times(1)).findAllByLastNameLike(anyString());

    }

    @Test
    public void shouldProcessFindFormReturnOne() throws Exception {
        System.out.println("Owner Controller Tes - shouldProcessFindFormReturnOne");

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(ownerService, times(1)).findAllByLastNameLike(anyString());

    }

    @Test
    public void shouldShowOwner() throws Exception {

        mockMvc.perform(get("/owners/1/show")).andExpect(status().isOk()).andExpect(view().name("owners/1/show"));

        verify(ownerService, times(1)).findById(1L);

    }

    @Test
    public void shouldDisplayOwner() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(123L).build());

        mockMvc.perform(get("/owners/123")).andExpect(status().isOk()).andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(123L))));

        verify(ownerService, times(1)).findById(123L);

    }
}

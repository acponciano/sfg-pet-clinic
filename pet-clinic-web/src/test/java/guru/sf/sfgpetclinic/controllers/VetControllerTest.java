package guru.sf.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.sf.sfgpetclinic.model.Vet;
import guru.sf.sfgpetclinic.services.VetService;

@ExtendWith(SpringExtension.class)
public class VetControllerTest {

    @Mock
    VetService service;

    @InjectMocks
    VetController controller;

    Set<Vet> vets;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testmockMVCIndexUrl() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler()).build();

        mockMvc.perform(get("/vets/index.html")).andExpect(view().name("vets/index")).andExpect(status().isOk());

    }

    @Test
    public void shouldListVets() throws Exception {

        when(service.findAll()).thenReturn(vets);

        mockMvc.perform(get("/vets")).andExpect(status().isOk()).andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));

        verify(service, times(1)).findAll();
    }

    @Test
    public void shouldFindVets() throws Exception {

        mockMvc.perform(get("/vets/find")).andExpect(status().isOk()).andExpect(view().name("notImplemented"));

        verifyNoInteractions(service);

    }

    @Test
    public void shouldShowVet() throws Exception {

        mockMvc.perform(get("/vets/1/show")).andExpect(status().isOk()).andExpect(view().name("vets/show"));

        verify(service, times(1)).findById(1L);

    }

    @Test
    public void shouldUpdateVetForm() throws Exception {

        mockMvc.perform(get("/vets/1/update")).andExpect(status().isOk()).andExpect(view().name("vets/update"));

        verify(service, times(1)).findById(1L);

    }

    @Test
    public void shouldUpdateVet() throws Exception {

        // Given
        Vet vet = new Vet(4L, "test First Name", "test Last Name");

        when(service.save(any())).thenReturn(vet);

        mockMvc.perform(post("/vets").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", vet.getId() + "")
                .param("firstName", vet.getFirstName()).param("lastName", vet.getLastName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/vets/" + vet.getId() + "/show"));

        verify(service, times(1)).save(any());

    }

    @Test
    public void shouldAddVetForm() throws Exception {

        mockMvc.perform(get("/vets/add")).andExpect(status().isOk()).andExpect(view().name("vets/update"));

        verify(service, times(0)).findById(any());

    }

    @Test
    public void shouldAddVet() throws Exception {

        // Given
        Vet vet = new Vet(4L, "test new First Name", "test new Last Name");

        when(service.save(any())).thenReturn(vet);

        mockMvc.perform(post("/vets").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
                .param("firstName", vet.getFirstName()).param("lastName", vet.getLastName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/vets/" + vet.getId() + "/show"));

        verify(service, times(1)).save(any());

    }

}

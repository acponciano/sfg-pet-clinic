package guru.sf.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.sf.sfgpetclinic.model.Image;
import guru.sf.sfgpetclinic.services.ImageService;

@ExtendWith(SpringExtension.class)
public class ImageControllerTest {

    @Mock
    ImageService service;

    @InjectMocks
    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        controller = new ImageController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getImageForm() throws Exception {
        // given
        Image image = new Image();
        image.setId(1L);

        when(service.findById(anyLong())).thenReturn(image);

        // when
        mockMvc.perform(get("/images/1")).andExpect(status().isOk()).andExpect(model().attributeExists("image"));

        verify(service, times(1)).findById(anyLong());

    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        Image image = new Image();
        Byte[] data = new Byte["Spring Framework Guru".length()];

        int i = 0;
        for (byte b : "Spring Framework Guru".getBytes()) {
            data[i++] = b;
        }

        image.setId(1L);
        image.setData(data);

        when(service.save(any())).thenReturn(image);

        mockMvc.perform(multipart("/images/vets/1").file(multipartFile)).andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/vets/1/saveImage/1"));

        verify(service, times(1)).save(any());
    }

    @Test
    public void renderImageFromDB() throws Exception {

        // given
        Image image = new Image();
        image.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        image.setData(bytesBoxed);

        when(service.findById(anyLong())).thenReturn(image);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/images/1/show")).andExpect(status().isOk()).andReturn()
                .getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, reponseBytes.length);
    }

    @Test
    public void shouldHandleNumberFormatException() throws Exception {

        mockMvc.perform(get("/images/ASDF/show")).andExpect(status().isBadRequest()).andExpect(view().name("400error"))
                .andExpect(model().attributeExists("exception"));
    }

}
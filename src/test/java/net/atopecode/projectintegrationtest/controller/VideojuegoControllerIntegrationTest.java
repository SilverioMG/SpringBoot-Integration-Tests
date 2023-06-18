package net.atopecode.projectintegrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.atopecode.projectintegrationtest.model.TipoJuego;
import net.atopecode.projectintegrationtest.model.Videojuego;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class VideojuegoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getOne() throws Exception {
        MvcResult result = mockMvc.perform(
                get(VideojuegoControllerTestUtils.getUrl("/0")).
                        accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Videojuego videojuego = objectMapper.readValue(result.getResponse().getContentAsString(), Videojuego.class);
        assertNotNull(videojuego);
    }

    @Test
    public void save() throws Exception {
        final String nombre = "Street Fighter";
        final int puntuacion = 10;

        Videojuego videojuego = new Videojuego(nombre, puntuacion, TipoJuego.PLATAFORMAS);

        MvcResult result =mockMvc.perform(
                MockMvcRequestBuilders.post(VideojuegoControllerTestUtils.getUrl(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(videojuego)))
                .andExpect(status().isCreated())
                .andReturn();

        videojuego = objectMapper.readValue(result.getResponse().getContentAsString(), Videojuego.class);

        assertNotNull(videojuego);
        assertEquals(nombre, videojuego.getNombre());
        assertEquals(puntuacion, videojuego.getPuntuacion());
    }

}

package net.atopecode.projectintegrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.atopecode.projectintegrationtest.model.Videojuego;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class VideojuegoContollerDistinctFieldsMappingIntegrationTest {

    public static final String PATH_URL = "/videojuego";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveWithoutAnyFields() throws Exception {
        final String nombre = "Street Fighter";

        VideojuegoWithoutAnyFields videojuego = new VideojuegoWithoutAnyFields(nombre);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(VideojuegoControllerTestUtils.getUrl(""))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(videojuego)))
                .andExpect(status().isCreated())
                .andReturn();

        Videojuego videojuegoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Videojuego.class);

        assertNotNull(videojuegoResponse);
        assertEquals(nombre, videojuegoResponse.getNombre());
        assertNull(videojuegoResponse.getPuntuacion());
    }

    @Test
    public void saveWithoutMoreFields() throws Exception {
        final String nombre = "Street Fighter";
        final Integer putuacion = 10;
        final String comentarios = "Es un juego de lucha.";

        VideojuegoWithMoreFields videojuego = new VideojuegoWithMoreFields(nombre, putuacion, comentarios);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(VideojuegoControllerTestUtils.getUrl(""))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(videojuego)))
                .andExpect(status().isCreated())
                .andReturn();

        Videojuego videojuegoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Videojuego.class);

        assertNotNull(videojuegoResponse);
        assertEquals(nombre, videojuegoResponse.getNombre());
        assertEquals(putuacion, videojuegoResponse.getPuntuacion());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    public static class VideojuegoWithoutAnyFields {
        private String nombre;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    public static class VideojuegoWithMoreFields {
        private String nombre;
        private Integer puntuacion;
        private String comentarios;
    }
}

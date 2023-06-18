package net.atopecode.projectintegrationtest.controller.json;

import net.atopecode.projectintegrationtest.model.TipoJuego;
import net.atopecode.projectintegrationtest.model.Videojuego;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@JsonTest
public class DeserializeTest {

    @Autowired
    private JacksonTester<Videojuego> jackson;

    @Test
    public void deserialize() throws IOException {
        final String nombre = "Mario Bros";
        final Integer puntuacion = 9;
        final TipoJuego tipoJuego = TipoJuego.PLATAFORMAS;

        Videojuego videojuegoToJson = new Videojuego(nombre, puntuacion, tipoJuego);
        String json = serializeManualJson(videojuegoToJson);

        Videojuego videojuego = jackson.parseObject(json);

        assertEquals(nombre, videojuego.getNombre());
        assertEquals(puntuacion, videojuego.getPuntuacion());
        assertEquals(tipoJuego, videojuego.getTipoJuego());
    }

    @Test
    public void deserializeWithNullOrEmptyValues() throws IOException {
        final String nombre = "";
        final Integer puntuacion = null;
        final TipoJuego tipoJuego = null;

        Videojuego videojuegoToJson = new Videojuego(nombre, puntuacion, tipoJuego);
        String json = serializeManualJson(videojuegoToJson);

        Videojuego videojuego = jackson.parseObject(json);

        assertEquals(nombre, videojuego.getNombre());
        assertEquals(puntuacion, videojuego.getPuntuacion());
        assertEquals(tipoJuego, videojuego.getTipoJuego());
    }

    @Test
    public void deserializeAndValidateOk() throws IOException {
        final String nombre = "Mario Bros";
        final Integer puntuacion = 9;
        final TipoJuego tipoJuego = TipoJuego.PLATAFORMAS;

        Videojuego videojuegoToJson = new Videojuego(nombre, puntuacion, tipoJuego);
        String json = serializeManualJson(videojuegoToJson);

        Videojuego videojuego = jackson.parseObject(json);

        assertEquals(nombre, videojuego.getNombre());
        assertEquals(puntuacion, videojuego.getPuntuacion());
        assertEquals(tipoJuego, videojuego.getTipoJuego());

        ValidationUtils.validate(videojuego);
    }

    @Test
    public void deserializeAndValidateKo() throws IOException {
        final String nombre = "";
        final Integer puntuacion = null;
        final TipoJuego tipoJuego = null;

        Videojuego videojuegoToJson = new Videojuego(nombre, puntuacion, tipoJuego);
        String json = serializeManualJson(videojuegoToJson);

        Videojuego videojuego = jackson.parseObject(json);

        assertEquals(nombre, videojuego.getNombre());
        assertEquals(puntuacion, videojuego.getPuntuacion());
        assertEquals(tipoJuego, videojuego.getTipoJuego());

        assertThrows(ConstraintViolationException.class, () -> {
            ValidationUtils.validate(videojuego);
        });
    }

    private String serializeManualJson(Videojuego videojuego) {
        JsonManualSerializer json = new JsonManualSerializer();
        json.init();
        json.addFieldName("nombre");
        json.addFieldValue(videojuego.getNombre());
        json.addFieldName("puntuacion");
        json.addFieldValue(videojuego.getPuntuacion());
        json.addFieldName("tipoJuego");
        json.addFieldValue(videojuego.getTipoJuego(), false);
        json.finish();
        return json.getResult();
    }

}

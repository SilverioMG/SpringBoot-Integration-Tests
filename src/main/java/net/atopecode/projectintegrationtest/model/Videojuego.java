package net.atopecode.projectintegrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Videojuego {

    private String nombre;
    private Integer puntuacion;
}

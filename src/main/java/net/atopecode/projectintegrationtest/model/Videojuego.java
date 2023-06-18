package net.atopecode.projectintegrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Videojuego {

    @NotBlank
    private String nombre;

    @NotNull
    private Integer puntuacion;

    @NotNull
    private TipoJuego tipoJuego;
}

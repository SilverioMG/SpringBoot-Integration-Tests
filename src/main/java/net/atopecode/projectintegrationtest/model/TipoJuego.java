package net.atopecode.projectintegrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoJuego {
    ARCADE("ARC", "Arcade"),
    PLATAFORMAS("PTL", "Plataformas");

    private String codigo;
    private String descripcion;
}

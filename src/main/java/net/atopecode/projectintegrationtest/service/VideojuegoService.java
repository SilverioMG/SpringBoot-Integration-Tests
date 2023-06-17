package net.atopecode.projectintegrationtest.service;

import net.atopecode.projectintegrationtest.model.Videojuego;

public interface VideojuegoService {

    Videojuego getOne(int index);
    Videojuego save(Videojuego videojuego);
}

package net.atopecode.projectintegrationtest.service;

import net.atopecode.projectintegrationtest.model.TipoJuego;
import net.atopecode.projectintegrationtest.model.Videojuego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    private List<Videojuego> videojuegos;

    public VideojuegoServiceImpl() {
        this.videojuegos = new ArrayList<>();
        init();
    }

    private void init() {
        this.videojuegos.add(new Videojuego("Tetris", 10, TipoJuego.ARCADE));
    }

    @Override
    public Videojuego getOne(int index) {
        if(index < 0) index = 0;
        if(index > (videojuegos.size() - 1)) index = videojuegos.size() - 1;
        return videojuegos.get(index);
    }

    @Override
    public Videojuego save(Videojuego videojuego) {
        Objects.requireNonNull(videojuego);
        videojuegos.add(videojuego);
        return videojuego;
    }
}

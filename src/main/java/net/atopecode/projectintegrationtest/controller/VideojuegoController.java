package net.atopecode.projectintegrationtest.controller;

import net.atopecode.projectintegrationtest.model.Videojuego;
import net.atopecode.projectintegrationtest.service.VideojuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videojuego")
public class VideojuegoController {

    private VideojuegoService videojuegoService;

    public VideojuegoController(
            VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping("/{index}")
    public ResponseEntity<Videojuego> getOne(@PathVariable int index) {
        Videojuego videojuego = videojuegoService.getOne(index);
        return ResponseEntity.ok(videojuego);
    }

    @PostMapping
    public ResponseEntity<Videojuego> save(@RequestBody Videojuego videojuego) {
        videojuego = videojuegoService.save(videojuego);
        return new ResponseEntity<>(videojuego, HttpStatus.CREATED);
    }
}

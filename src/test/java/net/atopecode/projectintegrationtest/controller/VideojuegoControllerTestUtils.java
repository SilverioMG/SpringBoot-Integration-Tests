package net.atopecode.projectintegrationtest.controller;

public class VideojuegoControllerTestUtils {

    public static final String PATH_URL = "/videojuego";
    public static String getUrl(String subPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PATH_URL);
        stringBuilder.append(subPath);
        return stringBuilder.toString();
    }
}

package org.example;
public class Main {
    static void main() {
        Aliado aliado = new Aliado("Math", 4000, 300, 100, 150);
        Enemigo enemigo = new Enemigo("Examen", 14000, 200, 130, 80);
        Game game = new Game(aliado, enemigo);
        GameController partida = new GameController(game);
        partida.combate();
    }
}

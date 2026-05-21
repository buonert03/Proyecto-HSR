package org.example;
public class Main {
    static void main() {
        Aliado aliado = new Aliado("Math", 4000, 100, 250, 150);
        Enemigo enemigo = new Enemigo("Marta", 14000, 120, 150, 80);
        Game game = new Game(aliado, enemigo);
        GameController partida = new GameController(game);
        partida.combate();
    }
}

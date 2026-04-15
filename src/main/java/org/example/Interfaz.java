package org.example;

public class Interfaz {
private Game game;
public Double[] statsAliado = game.getStatsAliado();
public Double[] statsEnemigo = game.getStatsEnemigo();
    public void situacion () {

        System.out.printf("Aliado: % PS, % ATQ, % VEL, % VA, % ER", statsAliado[0],statsAliado[1],statsAliado[2],statsAliado[3],statsAliado[4]);
        System.out.printf("Enemigo: % PS, % ATQ, % VEL, % VA, % EQ", statsEnemigo[0],statsEnemigo[1],statsEnemigo[2],statsEnemigo[3],statsEnemigo[4]);
        System.out.printf("% PH", game.getPH());
    }

    public void seleccionarHabilidad() {
        System.out.println("Básico: 0 | Incremento: 1 | Curación: 2 | Especial: 3 | Definitiva: 4");
    }

    public void noUsable() {
        System.out.println("No puede activarse la habilidad");
    }
    public void cancerlar() {
        System.out.println("Cancelaste el uso de la habilidad");
    }
    public void basico(){
        Double vulnerable;
        if (statsEnemigo[4]-20 <= 0) {
            vulnerable = 1.3;
        } else {
            vulnerable = 1.0;
        }
        System.out.printf("Postura: -% | Daño: % | ER: % | PH: %", statsEnemigo[4]-20, statsAliado[1]*1.25*vulnerable, statsAliado[4]+25.0, game.getPH()+1);
    }
    public void incremento(){

    }
}

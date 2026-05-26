package org.example;

import java.util.Scanner;

public class GameController {
    private final Game game;
    private final Interfaz interfaz;
    private final Scanner habilidad = new Scanner(System.in);
    private final Scanner usar = new Scanner(System.in);

    public GameController(Game game) {
        interfaz = new Interfaz();
        this.game = game;
    }

    public void combate() {
        interfaz.tutorial();
        interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH(), game.getPhMax());
        int secuenciaAtaqueEnemigo = 0;
        while (!game.acabaCombate()) {

            String accion = game.valorAccionCombate();

            if (accion.equals("Turno aliado")) {
                boolean turnoAliado = true;
                interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH(), game.getPhMax());
                while (turnoAliado) {
                    interfaz.seleccionarHabilidad();
                    String habilidadEscogida = habilidad.nextLine();

                    if (game.habilidadValida(habilidadEscogida)) {

                        switch (habilidadEscogida) {
                            case "1" -> {
                                interfaz.basico(game.getAliado(), game.getEnemigo(), game.getPH(), game.getPhMax());
                                interfaz.quieresUsarLa(habilidadEscogida);
                                if (usar.next().equalsIgnoreCase("S")) {
                                    game.usarBasico();
                                    turnoAliado = false;
                                } else {
                                    interfaz.cancelar();
                                }
                            }
                            case "2" -> {
                                interfaz.incremento(game.getAliado(), game.getPH());
                                if (game.getPH() >= 2) {
                                    interfaz.quieresUsarLa(habilidadEscogida);
                                    if (usar.next().equalsIgnoreCase("S")) {
                                        game.usarIncremento();
                                        turnoAliado = false;
                                    } else {
                                        interfaz.cancelar();
                                    }
                                } else {
                                    interfaz.noUsable(2.0);
                                }
                            }
                            case "3" -> {
                                interfaz.curacion(game.getAliado(), game.getPH());
                                if (game.getPH() >= 1) {
                                    interfaz.quieresUsarLa(habilidadEscogida);
                                    if (usar.next().equalsIgnoreCase("S")) {
                                        game.usarCuracion();
                                        turnoAliado = false;
                                    } else {
                                        interfaz.cancelar();
                                    }
                                } else {
                                    interfaz.noUsable(1.0);
                                }
                            }
                            case "4" -> {
                                interfaz.especial(game.getAliado(), game.getEnemigo(), game.getPH());
                                if (game.getPH() >= 1) {
                                    interfaz.quieresUsarLa(habilidadEscogida);
                                    if (usar.next().equalsIgnoreCase("S")) {
                                        game.usarEspecial();
                                        turnoAliado = false;
                                    } else {
                                        interfaz.cancelar();
                                    }
                                } else {
                                    interfaz.noUsable(1.0);
                                }
                            }
                            case "5" -> {
                                interfaz.definitiva(game.getAliado(), game.getEnemigo(), game.getPH(), game.getPhMax());
                                if (game.getAliado().getEnergia() == game.getAliado().getEnergiaMax()) {
                                    interfaz.quieresUsarLa(habilidadEscogida);
                                    if (usar.next().equalsIgnoreCase("S")) {
                                        game.usarDefinitiva();
                                        turnoAliado = false;
                                    } else {
                                        interfaz.cancelar();
                                    }
                                } else {
                                    interfaz.noEnergia(game.getAliado().getEnergiaMax(), game.getAliado().getEnergia());
                                }
                            }
                        }

                    } else {
                        System.out.println("Input inválido, vuelve a probar.");
                    }
                }
            } else if (accion.equals("Turno enemigo")) {
                game.restablecerEq();
                interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH(), game.getPhMax());
                secuenciaAtaqueEnemigo = secuenciaAtaqueEnemigo + 1;
                if (secuenciaAtaqueEnemigo > 7) {
                    secuenciaAtaqueEnemigo = 1;
                }

                switch (secuenciaAtaqueEnemigo) {
                    case 1:
                    case 3:
                    case 6:
                        interfaz.enemigoBasico(game.getAliado(), game.getEnemigo());
                        game.enemigoBasico();
                        break;
                    case 2:
                        interfaz.enemigoDebufo(game.getAliado(), game.getEnemigo());
                        game.enemigoDebuff();
                        break;
                    case 4:
                        interfaz.enemigoAutoBufo(game.getAliado(), game.getEnemigo());
                        game.enemigoAutobufo();
                        break;
                    case 5:
                        interfaz.enemigoQuitar(game.getAliado(), game.getEnemigo(), game.getPH());
                        game.enemigoQuitarPH();
                        break;
                    case 7:
                        interfaz.enemigoDefinitiva(game.getAliado(), game.getEnemigo());
                        game.enemigoDefinitiva();
                        break;
                }
            }
        }
    }
}

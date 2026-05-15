package org.example;

import java.util.Scanner;

public class GameController {
    private Game game;
    private Interfaz interfaz;
    private Scanner habilidad = new Scanner(System.in);
    private Scanner usar = new Scanner(System.in);

    public GameController() {
        interfaz = new Interfaz();
        game = new Game();
    }

    public void combate() {
        interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH());
        int secuenciaAtaqueEnemigo = 0;
        while (game.acabaCombate() == false) {

            String accion = game.valorAccionCombate();

            if (accion.equals("Turno aliado")) {
                Boolean turnoAliado = true;
                interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH());
                while (turnoAliado == true) {
                    interfaz.seleccionarHabilidad();
                    int habilidadEscogida = habilidad.nextInt();

                    if (game.habilidadValida(habilidadEscogida) == true) {

                        if (habilidadEscogida == 1) {
                            interfaz.basico(game.getAliado(), game.getEnemigo(), game.getPH());
                            interfaz.quieresUsarLa(habilidadEscogida);
                            if (usar.next().equalsIgnoreCase("S")) {
                                game.usarBasico();
                                turnoAliado = false;
                            } else {
                                interfaz.cancelar();
                            }
                            
                        } else if (habilidadEscogida == 2 && game.getPH() >= 2) {
                            interfaz.incremento(game.getAliado(), game.getPH());
                            interfaz.quieresUsarLa(habilidadEscogida);
                            if (usar.next().equalsIgnoreCase("S")) {
                                if (game.getPH() >= 2) {
                                    game.usarIncremento();
                                    turnoAliado = false;
                                } else {
                                    interfaz.noUsable();
                                }
                            } else {
                                interfaz.cancelar();
                            }

                        } else if (habilidadEscogida == 3 && game.getPH() >= 1) {
                            interfaz.curacion(game.getAliado(), game.getPH());
                            interfaz.quieresUsarLa(habilidadEscogida);
                            if (usar.next().equalsIgnoreCase("S")) {
                                if (game.getPH() >= 1) {
                                    game.usarCuracion();
                                    turnoAliado = false;
                                } else {
                                    interfaz.noUsable();
                                }
                            } else {
                                interfaz.cancelar();
                            }

                        } else if (habilidadEscogida == 4 && game.getPH() >= 1) {
                            interfaz.especial(game.getAliado(), game.getEnemigo(), game.getPH());
                            interfaz.quieresUsarLa(habilidadEscogida);
                            if (usar.next().equalsIgnoreCase("S")) {
                                if (game.getPH() >= 1) {
                                    game.usarEspecial();
                                    turnoAliado = false;
                                } else {
                                    interfaz.noUsable();
                                }
                            } else {
                                interfaz.cancelar();
                            }

                        } else if (habilidadEscogida == 5 && game.getAliado().getEnergia() == game.getAliado().getEnergiaMax()) {
                            interfaz.definitiva(game.getAliado(), game.getEnemigo(), game.getPH());
                            interfaz.quieresUsarLa(habilidadEscogida);
                            if (usar.next().equalsIgnoreCase("S")) {
                                if (game.getAliado().getEnergia() == game.getAliado().getEnergiaMax()) {
                                    game.usarDefinitiva();
                                    turnoAliado = false;
                                } else {
                                    interfaz.noUsable();
                                }
                            } else {
                                interfaz.cancelar();
                            }

                        }
                    } else {
                        System.out.println("Input inválido, vuelve a probar.");
                    }
                }
            } else if (accion.equals("Turno enemigo")) {
                game.reestablecerEq();
                interfaz.situacion(game.getAliado(), game.getEnemigo(), game.getPH());
                secuenciaAtaqueEnemigo = secuenciaAtaqueEnemigo + 1;
                if (secuenciaAtaqueEnemigo > 7) {
                    secuenciaAtaqueEnemigo = 1;
                }
                if (secuenciaAtaqueEnemigo == 1) {
                    interfaz.enemigoBasico(game.getAliado(), game.getEnemigo());
                    game.enemigoBasico();
                } else if (secuenciaAtaqueEnemigo == 2) {
                    interfaz.enemigoDebufo(game.getAliado(), game.getEnemigo());
                    game.enemigoDebuff();
                } else if (secuenciaAtaqueEnemigo == 3) {
                    interfaz.enemigoBasico(game.getAliado(), game.getEnemigo());
                    game.enemigoBasico();
                } else if (secuenciaAtaqueEnemigo == 4) {
                    interfaz.enemigoAutoBufo(game.getAliado(), game.getEnemigo());
                    game.enemigoAutobufo();
                } else if (secuenciaAtaqueEnemigo == 5) {
                    interfaz.enemigoQuitar(game.getAliado(), game.getEnemigo(), game.getPH());
                    game.enemigoQuitarPH();
                } else if (secuenciaAtaqueEnemigo == 6) {
                    interfaz.enemigoBasico(game.getAliado(), game.getEnemigo());
                    game.enemigoBasico();
                } else if (secuenciaAtaqueEnemigo == 7) {
                    interfaz.enemigoDefinitiva(game.getAliado(), game.getEnemigo());
                    game.enemigoDefinitiva();
                }
            }
        }
    }
}

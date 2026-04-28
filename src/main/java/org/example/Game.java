package org.example;

import java.util.ArrayList;

public class Game {
    private double phMax = 5;
    private double ph = 3;
    private Aliado aliado;
    private Double[] statsAliado = new Double[]{aliado.getPsActual(), aliado.getAtqActual(), aliado.getVelActual(), aliado.getVaActual(), aliado.getEnergia()};
    private Enemigo enemigo;
    private Double[] statsEnemigo = new Double[]{enemigo.getPsActual(), enemigo.getAtqActual(), enemigo.getVelActual(), enemigo.getVaActual(), enemigo.getEquilibrio()};
    private boolean finCombate = false;
    private ArrayList<Double> valoresAccion;

    public Double[] getStatsAliado() {
        return statsAliado;
    }
    public Double[] getStatsEnemigo() {
        return statsEnemigo;
    }

    // ---------------------------------------------------------------------------------------
    // ---------------------- GESTIÓN DE PUNTOS DE HABILIDAD ---------------------------------
    // ---------------------------------------------------------------------------------------

    public Double getPH() {return ph;}

    public void aumentarPH(Double aumento) {
        ph += aumento;
        if (ph > 5){
            ph = 5;
        }
    }

    public void reducirPH(Double reduccion) {
        ph -= reduccion;
        if (ph < 0) {
            ph = 0;
        }
    }

    // -------------------------------------------------------------------------------------
    // ------------------------ HABILIDADES DEL ALIADO -------------------------------------
    // -------------------------------------------------------------------------------------

        // Habilidad 0
    public void usarBasico() {
        aumentarPH(1.0);
        aliado.aumentarEnergia(25.0);
        enemigo.reducirEquilibrio(20.0);
        enemigo.perderPs(aliado.getAtqActual() * 1.25 * enemigo.getVulnerable());
        aliado.resetearVa();
    }

        // Habilidad 1
    public void usarIncremento() {
        reducirPH(2.0);
        aliado.aumentarEnergia(75.0);
        aliado.aumentarAtq(40);
        aliado.aumentarVel(100);
        aliado.resetearVa();
        aliado.adelantarAccion(50);
    }

        // Habilidad 2
    public void usarCuracion() {
        reducirPH(1.0);
        aumentarPH(1.0);
        aliado.curarPs(25);
        aliado.aumentarEnergia(25.0);
        aliado.resetearVa();
    }

        // Habilidad 3
    public void usarEspecial() {
        reducirPH(1.0);
        aliado.aumentarEnergia(50.0);
        enemigo.reducirEquilibrio(20.0);
        enemigo.reducirVel(20);
        enemigo.perderPs(aliado.getAtqActual() * 2.0 * enemigo.getVulnerable());
        aliado.resetearVa();
    }

        // Habilidad 4
    public void usarDefinitiva() {
        aliado.vaciarEnergia();
        aumentarPH(3.0);
        enemigo.reducirEquilibrio(40.0);
        enemigo.perderPs(aliado.getAtqActual() * 4.0 * enemigo.getVulnerable());
        aliado.resetearVa();
        aliado.adelantarAccion(25.0);
    }

    // ----------------------------------------------------------------------------------------
    // --------------------------- HABILIDADES DEL ENEMIGO ------------------------------------
    // ----------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------
    // ------------------------------ LÓGICA DEL COMBATE --------------------------------------
    // ----------------------------------------------------------------------------------------

    public void acabaCombate() {
        if (aliado.getPsActual() <= 0 || enemigo.getPsActual() <= 0) {
            finCombate = true;
        }
    }

    public void combate() {
        valoresAccion.add(aliado.getVaActual());
        valoresAccion.add(enemigo.getVaActual());
        while (!finCombate) {
            if (valoresAccion.get(0) <= 0) {

            } else if (valoresAccion.get(1) <= 0) {

            } else {
                aliado.avanzarVa();
                enemigo.avanzarVa();
            }
        }
    }
}

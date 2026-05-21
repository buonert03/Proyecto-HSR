package org.example;

import java.sql.SQLOutput;

public class Game {
    private double phMax = 5;
    private double ph = 3;
    private Aliado aliado = new Aliado("Math", 4000, 100, 250, 150);
    private Enemigo enemigo = new Enemigo("Marta", 14000, 120, 150, 80);

    public Game(Aliado aliado, Enemigo enemigo) {
        this.aliado = aliado;
        this.enemigo = enemigo;
    }


    public Aliado getAliado() {
        return this.aliado;
    }
    public Enemigo getEnemigo() {
        return this.enemigo;
    }

    // ---------------------- GESTIÓN DE PUNTOS DE HABILIDAD ---------------------------------
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

    // ------------------------ HABILIDADES DEL ALIADO -------------------------------------
    /**
     * @param habilidad Un número entero que representa cual habilidad se ha escogido y comprobar si es válida.
     * @return Valor booleano que representa si la habilidad dada es válida o no para el propósito del programa.
     */
    public boolean habilidadValida(int habilidad) {
        if (habilidad != 1 && habilidad != 2 && habilidad != 3 && habilidad != 4 && habilidad != 5) {
            return false;
        } else {
            return true;
        }
    }

        // Habilidad 1
    public void usarBasico() {
        aumentarPH(1.0);
        aliado.aumentarEnergia(25.0);
        enemigo.reducirEquilibrio(20.0);
        enemigo.perderPs(aliado.getAtqActual() * 1.25 * enemigo.getVulnerable());
        aliado.resetearVa();
    }

        // Habilidad 2
    public void usarIncremento() {
        reducirPH(2.0);
        aliado.aumentarEnergia(75.0);
        aliado.aumentarAtq(40);
        aliado.aumentarVel(30);
        aliado.resetearVa();
        aliado.adelantarAccion(50);
    }

        // Habilidad 3
    public void usarCuracion() {
        reducirPH(1.0);
        aumentarPH(1.0);
        aliado.curarPs(25);
        aliado.aumentarEnergia(25.0);
        aliado.resetearVa();
    }

        // Habilidad 4
    public void usarEspecial() {
        reducirPH(1.0);
        aliado.aumentarEnergia(50.0);
        enemigo.reducirEquilibrio(40.0);
        enemigo.reducirVel(20);
        enemigo.reducirAtq(10);
        enemigo.perderPs(aliado.getAtqActual() * 2.0 * enemigo.getVulnerable());
        aliado.resetearVa();
    }

        // Habilidad 5
    public void usarDefinitiva() {
        aliado.vaciarEnergia();
        aumentarPH(3.0);
        enemigo.reducirEquilibrio(50.0);
        enemigo.perderPs(aliado.getAtqActual() * 4.0 * enemigo.getVulnerable());
        aliado.resetearVa();
        aliado.adelantarAccion(25.0);
    }

    // --------------------------- HABILIDADES DEL ENEMIGO ------------------------------------
    public void reestablecerEq() {
        enemigo.restaurarEquilibrio();
    }

    // Habilidad 1
    public void enemigoBasico() {
        aliado.perderPs(enemigo.getAtqActual() * 1.2);
        enemigo.resetearVa();
    }

    // Habilidad 2
    public void enemigoQuitarPH() {
        aliado.perderPs(enemigo.getAtqActual() * 1.4);
        reducirPH(1.0);
        enemigo.resetearVa();
    }

    // Habilidad 3
    public void enemigoAutobufo() {
        enemigo.aumentarAtq(15);
        enemigo.aumentarVel(15);
        aliado.perderPs(enemigo.getAtqActual()*1.5);
        enemigo.resetearVa();
    }

    // Habilidad 4
    public void enemigoDebuff() {
        aliado.reducirAtq(10);
        aliado.reducirVel(10);
        aliado.reducirEnergia(10);
        aliado.perderPs(enemigo.getAtqActual() * 1.1);
        enemigo.resetearVa();
    }

    // Habilidad 5
    public void enemigoDefinitiva() {
        enemigo.curarPs(15);
        aliado.perderPs(enemigo.getAtqActual()*2);
        aliado.atrasarAccion(20);
        enemigo.resetearVa();
        enemigo.adelantarAccion(40);
    }

    // ------------------------------ LÓGICA DEL COMBATE --------------------------------------
    public boolean acabaCombate() {
        if (aliado.getPsActual() <= 0) {
            System.out.println("Acabó el combate");
            System.out.println("PERDISTE");
            return true;
        } else if (enemigo.getPsActual() <= 0) {
            System.out.println("Acabó el combate");
            System.out.println("GANASTE");
            return true;
        }
        return false;
    }
    public String valorAccionCombate() {
        if (aliado.getVaActual() <= 0) {
            System.out.println("---------------- TURNO " + aliado.getNombre() + " ----------------");
            return  "Turno aliado";
        } else if (enemigo.getVaActual() <= 0) {
            System.out.println("---------------- TURNO " + enemigo.getNombre() + " --------");
            return "Turno enemigo";
        } else {
            aliado.avanzarVa();
            enemigo.avanzarVa();
            return "Nada";
        }
    }
}

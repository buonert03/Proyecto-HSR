package org.example;

import java.util.HashMap;

public class Game {
    private final double phMax = 5;
    private double ph = 3;
    private final Aliado aliado;
    private final Enemigo enemigo;
    private HashMap<String, Double> resultadosAliado;
    private HashMap<String, Double> resultadosEnemigo;

    public Game(Aliado aliado, Enemigo enemigo) {
        this.aliado = aliado;
        this.enemigo = enemigo;
        this.resultadosAliado.put("Usos Habilidad 1: ", 0.0);
        this.resultadosAliado.put("Usos Habilidad 2: ", 0.0);
        this.resultadosAliado.put("Usos Habilidad 3: ", 0.0);
        this.resultadosAliado.put("Usos Habilidad 4: ", 0.0);
        this.resultadosAliado.put("Usos Habilidad 5: ", 0.0);
        this.resultadosAliado.put("Daño Total Infligido: ", 0.0);
        this.resultadosAliado.put("Daño Total Recibido: ", 0.0);

        this.resultadosEnemigo.put("Usos Habilidad 1: ", 0.0);
        this.resultadosEnemigo.put("Usos Habilidad 2: ", 0.0);
        this.resultadosEnemigo.put("Usos Habilidad 3: ", 0.0);
        this.resultadosEnemigo.put("Usos Habilidad 4: ", 0.0);
        this.resultadosEnemigo.put("Usos Habilidad 5: ", 0.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", 0.0);
        this.resultadosEnemigo.put("Daño Total Recibido: ", 0.0);
    }

    public Aliado getAliado() {
        return this.aliado;
    }
    public Enemigo getEnemigo() {
        return this.enemigo;
    }

    // ---------------------- GESTIÓN DE PUNTOS DE HABILIDAD ---------------------------------
    public double getPH() {return ph;}
    public double getPhMax() {return phMax;}
    public void aumentarPH(Double aumento) {
        ph += aumento;
        if (ph > phMax){
            ph = phMax;
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
    public boolean habilidadValida(String habilidad) {
        return habilidad.equals("1") || habilidad.equals("2") || habilidad.equals("3") || habilidad.equals("4") || habilidad.equals("5");
    }

        // Habilidad 1
    public void usarBasico() {
        aumentarPH(1.0);
        aliado.aumentarEnergia(25.0);
        enemigo.reducirEquilibrio(20.0);
        double daño = aliado.getAtqActual() * 1.25 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(daño);
        aliado.resetearVa();
        this.resultadosAliado.put("Usos Habilidad 1: ", this.resultadosAliado.get("Usos Habilidad 1: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+daño);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ")+daño);
    }

        // Habilidad 2
    public void usarIncremento() {
        reducirPH(2.0);
        aliado.aumentarEnergia(75.0);
        aliado.aumentarAtq(40);
        aliado.aumentarVel(30);
        aliado.resetearVa();
        aliado.adelantarAccion(50);
        this.resultadosAliado.put("Usos Habilidad 2: ", this.resultadosAliado.get("Usos Habilidad 2: ")+ 1.0);
    }

        // Habilidad 3
    public void usarCuracion() {
        reducirPH(1.0);
        aumentarPH(1.0);
        aliado.curarPs(25);
        aliado.aumentarEnergia(25.0);
        aliado.resetearVa();
        this.resultadosAliado.put("Usos Habilidad 3: ", this.resultadosAliado.get("Usos Habilidad 3: ")+ 1.0);
    }

        // Habilidad 4
    public void usarEspecial() {
        reducirPH(1.0);
        aliado.aumentarEnergia(50.0);
        enemigo.reducirEquilibrio(40.0);
        enemigo.reducirVel(20);
        enemigo.reducirAtq(10);
        double daño = aliado.getAtqActual() * 2.0 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(daño);
        aliado.resetearVa();
        this.resultadosAliado.put("Usos Habilidad 4: ", this.resultadosAliado.get("Usos Habilidad 4: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+daño);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ")+daño);
    }

        // Habilidad 5
    public void usarDefinitiva() {
        aliado.vaciarEnergia();
        aumentarPH(3.0);
        enemigo.reducirEquilibrio(50.0);
        double daño = aliado.getAtqActual() * 4.0 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(daño);
        aliado.resetearVa();
        aliado.adelantarAccion(25.0);
        this.resultadosAliado.put("Usos Habilidad 5: ", this.resultadosAliado.get("Usos Habilidad 5: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+daño);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ")+daño);
    }

    // --------------------------- HABILIDADES DEL ENEMIGO ------------------------------------
    public void restablecerEq() {
        enemigo.restaurarEquilibrio();
    }

    // Habilidad 1
    public void enemigoBasico() {
        double daño = enemigo.getAtqActual() * 1.2;
        aliado.perderPs(daño);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 1: ", this.resultadosEnemigo.get("Usos Habilidad 1: ") + 1.0);
        this.resultadosEnemigo.put("Daño Infligido Total: ", this.resultadosEnemigo.get("Daño Infligido Total: ") + daño);
        this.resultadosAliado.put("Daño Recibido Total: ", this.resultadosAliado.get("Daño Recibido Total: ") + daño);
    }

    // Habilidad 2
    public void enemigoQuitarPH() {
        double daño = enemigo.getAtqActual() * 1.4;
        aliado.perderPs(daño);
        reducirPH(1.0);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 2: ", this.resultadosEnemigo.get("Usos Habilidad 2: ") + 1.0);
        this.resultadosEnemigo.put("Daño Infligido Total: ", this.resultadosEnemigo.get("Daño Infligido Total: ") + daño);
        this.resultadosAliado.put("Daño Recibido Total: ", this.resultadosAliado.get("Daño Recibido Total: ") + daño);
    }

    // Habilidad 3
    public void enemigoAutobufo() {
        enemigo.aumentarAtq(15);
        enemigo.aumentarVel(15);
        double daño = enemigo.getAtqActual()*1.5;
        aliado.perderPs(daño);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 3: ", this.resultadosEnemigo.get("Usos Habilidad 3: ") + 1.0);
        this.resultadosEnemigo.put("Daño Infligido Total: ", this.resultadosEnemigo.get("Daño Infligido Total: ") + daño);
        this.resultadosAliado.put("Daño Recibido Total: ", this.resultadosAliado.get("Daño Recibido Total: ") + daño);
    }

    // Habilidad 4
    public void enemigoDebuff() {
        aliado.reducirAtq(10);
        aliado.reducirVel(10);
        aliado.reducirEnergia(10);
        double daño = enemigo.getAtqActual() * 1.1;
        aliado.perderPs(daño);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 4: ", this.resultadosEnemigo.get("Usos Habilidad 4: ") + 1.0);
        this.resultadosEnemigo.put("Daño Infligido Total: ", this.resultadosEnemigo.get("Daño Infligido Total: ") + daño);
        this.resultadosAliado.put("Daño Recibido Total: ", this.resultadosAliado.get("Daño Recibido Total: ") + daño);
    }

    // Habilidad 5
    public void enemigoDefinitiva() {
        enemigo.curarPs(15);
        double daño = enemigo.getAtqActual()*2;
        aliado.perderPs(daño);
        aliado.atrasarAccion(20);
        enemigo.resetearVa();
        enemigo.adelantarAccion(40);
        this.resultadosEnemigo.put("Usos Habilidad 5: ", this.resultadosEnemigo.get("Usos Habilidad 5: ") + 1.0);
        this.resultadosEnemigo.put("Daño Infligido Total: ", this.resultadosEnemigo.get("Daño Infligido Total: ") + daño);
        this.resultadosAliado.put("Daño Recibido Total: ", this.resultadosAliado.get("Daño Recibido Total: ") + daño);
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

package org.example;

import java.util.HashMap;
import java.util.List;

public class Game {
    private final double phMax = 5;
    private double ph = 3;
    private final Aliado aliado;
    private final Enemigo enemigo;
    List<String> claves = List.of("Usos Habilidad 1: ", "Usos Habilidad 2: ", "Usos Habilidad 3: ", "Usos Habilidad 4: ", "Usos Habilidad 5: ", "Daño Total Infligido: ", "Daño Total Recibido: ");
    private final HashMap<String, Double> resultadosAliado;
    private final HashMap<String, Double> resultadosEnemigo;

    public Game(Aliado aliado, Enemigo enemigo) {
        this.aliado = aliado;
        this.enemigo = enemigo;
        this.resultadosAliado = new HashMap<>();
        this.resultadosEnemigo = new HashMap<>();

        for (String c : this.claves) {
            this.resultadosAliado.put(c, 0.0);
            this.resultadosEnemigo.put(c, 0.0);
        }
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
        double dmg = aliado.getAtqActual() * 1.25 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(dmg);
        aliado.resetearVa();
        this.resultadosAliado.put("Usos Habilidad 1: ", this.resultadosAliado.get("Usos Habilidad 1: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+dmg);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosEnemigo.get("Daño Total Recibido: ")+dmg);
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
        double dmg = aliado.getAtqActual() * 2.0 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(dmg);
        aliado.resetearVa();
        this.resultadosAliado.put("Usos Habilidad 4: ", this.resultadosAliado.get("Usos Habilidad 4: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+dmg);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosEnemigo.get("Daño Total Recibido: ")+dmg);
    }

        // Habilidad 5
    public void usarDefinitiva() {
        aliado.vaciarEnergia();
        aumentarPH(3.0);
        enemigo.reducirEquilibrio(50.0);
        double dmg = aliado.getAtqActual() * 4.0 * enemigo.getVulnerable().getVulnerable();
        enemigo.perderPs(dmg);
        aliado.resetearVa();
        aliado.adelantarAccion(25.0);
        this.resultadosAliado.put("Usos Habilidad 5: ", this.resultadosAliado.get("Usos Habilidad 5: ")+ 1.0);
        this.resultadosAliado.put("Daño Total Infligido: ", this.resultadosAliado.get("Daño Total Infligido: ")+dmg);
        this.resultadosEnemigo.put("Daño Total Recibido: ", this.resultadosEnemigo.get("Daño Total Recibido: ")+dmg);
    }

    // --------------------------- HABILIDADES DEL ENEMIGO ------------------------------------
    public void restablecerEq() {
        enemigo.restaurarEquilibrio();
    }

    // Habilidad 1
    public void enemigoBasico() {
        double dmg = enemigo.getAtqActual() * 1.2;
        aliado.perderPs(dmg);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 1: ", this.resultadosEnemigo.get("Usos Habilidad 1: ") + 1.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", this.resultadosEnemigo.get("Daño Total Infligido: ") + dmg);
        this.resultadosAliado.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ") + dmg);
    }

    // Habilidad 2
    public void enemigoQuitarPH() {
        double dmg = enemigo.getAtqActual() * 1.4;
        aliado.perderPs(dmg);
        reducirPH(1.0);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 2: ", this.resultadosEnemigo.get("Usos Habilidad 2: ") + 1.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", this.resultadosEnemigo.get("Daño Total Infligido: ") + dmg);
        this.resultadosAliado.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ") + dmg);
    }

    // Habilidad 3
    public void enemigoAutobufo() {
        enemigo.aumentarAtq(15);
        enemigo.aumentarVel(15);
        double dmg = enemigo.getAtqActual()*1.5;
        aliado.perderPs(dmg);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 3: ", this.resultadosEnemigo.get("Usos Habilidad 3: ") + 1.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", this.resultadosEnemigo.get("Daño Total Infligido: ") + dmg);
        this.resultadosAliado.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ") + dmg);
    }

    // Habilidad 4
    public void enemigoDebuff() {
        aliado.reducirAtq(10);
        aliado.reducirVel(10);
        aliado.reducirEnergia(10);
        double dmg = enemigo.getAtqActual() * 1.1;
        aliado.perderPs(dmg);
        enemigo.resetearVa();
        this.resultadosEnemigo.put("Usos Habilidad 4: ", this.resultadosEnemigo.get("Usos Habilidad 4: ") + 1.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", this.resultadosEnemigo.get("Daño Total Infligido: ") + dmg);
        this.resultadosAliado.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ") + dmg);
    }

    // Habilidad 5
    public void enemigoDefinitiva() {
        enemigo.curarPs(15);
        double dmg = enemigo.getAtqActual()*2;
        aliado.perderPs(dmg);
        aliado.atrasarAccion(20);
        enemigo.resetearVa();
        enemigo.adelantarAccion(40);
        this.resultadosEnemigo.put("Usos Habilidad 5: ", this.resultadosEnemigo.get("Usos Habilidad 5: ") + 1.0);
        this.resultadosEnemigo.put("Daño Total Infligido: ", this.resultadosEnemigo.get("Daño Total Infligido: ") + dmg);
        this.resultadosAliado.put("Daño Total Recibido: ", this.resultadosAliado.get("Daño Total Recibido: ") + dmg);
    }

    // ------------------------------ LÓGICA DEL COMBATE --------------------------------------
    public boolean acabaCombate() {
        if (aliado.getPsActual() <= 0 || enemigo.getPsActual() <= 0) {
            System.out.println("Acabó el combate");

            if (aliado.getPsActual() <= 0) {
                System.out.println("PERDISTE");
            } else if (enemigo.getPsActual() <= 0) {
                System.out.println("GANASTE");
            }
            System.out.println();
            System.out.println("Resultados de " + aliado.getNombre());
            for (String clave : claves) {
                System.out.println(clave + resultadosAliado.get(clave));
            }
            System.out.println();
            System.out.println("Resultados de " + enemigo.getNombre());
            for (String clave : claves) {
                System.out.println(clave + resultadosEnemigo.get(clave));
            }
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

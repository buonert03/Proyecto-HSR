package org.example;

public class Enemigo extends Entidad {
    private final double equilibrioMax;
    private double equilibrio;
    private Vulnerabilidad vul;


    // ---------------- CONSTRUCTOR ----------------
    public Enemigo(String nombre, double psMax, double atqBase, double velBase, double equilibrioMax) {
        super(nombre, psMax, atqBase, velBase);
        this.equilibrioMax = equilibrioMax;
        this.equilibrio = equilibrioMax;
        this.vul = Vulnerabilidad.NO;
    }

    // ---------------- NOMBRE ----------------
    @Override
    protected String getNombre() {
        return super.getNombre();
    }

    // ---------------- SALUD ----------------
    @Override
    protected double getPsMax() {
        return super.getPsMax();
    }
    @Override
    protected double getPsActual() {
        return super.getPsActual();
    }
    @Override
    protected void perderPs(double danoRecibido) {
        super.perderPs(danoRecibido);
    }
    @Override
    protected void curarPs(double curaPorcentaje) {
        super.curarPs(curaPorcentaje);
    }

    // ---------------- ATAQUE ----------------
    @Override
    protected double getAtqBase() {
        return super.getAtqBase();
    }
    @Override
    protected double getAtqActual() {
        return super.getAtqActual();
    }
    @Override
    protected void reducirAtq(double reduccion) {
        super.reducirAtq(reduccion);
    }
    @Override
    protected void aumentarAtq(double aumento) {
        super.aumentarAtq(aumento);
    }

    // ---------------- VELOCIDAD ----------------
    @Override
    protected double getVelBase() {
        return super.getVelBase();
    }
    @Override
    protected double getVelActual() {
        return super.getVelActual();
    }
    @Override
    protected void reducirVel(double aumento) {
        super.reducirVel(aumento);
    }
    @Override
    protected void aumentarVel(double reduccion) {
        super.aumentarVel(reduccion);
    }

    // ---------------- VALOR DE ACCIÓN ----------------
    @Override
    protected double getVaActual() {
        return super.getVaActual();
    }
    @Override
    protected void resetearVa() {
        super.resetearVa();
    }
    @Override
    protected void avanzarVa() {
        super.avanzarVa();
    }
    @Override
    protected void atrasarAccion(double adelanto) {
        super.atrasarAccion(adelanto);
    }
    @Override
    protected void adelantarAccion(double atraso) {
        super.adelantarAccion(atraso);
    }

    // ---------------- EQUILIBRIO ----------------
    /**
     * Consigue el valor de Equilibrio Máximo del Enemigo.
     * @return Valor que representa el Equilibrio Máximo.
     */
    public double getEquilibrioMax() {
        return equilibrioMax;
    }
    /**
     * Consigue el valor de Equilibrio actual del Enemigo.
     * @return Valor que representa la cantidad de Equilibrio actual.
     */
    public double getEquilibrio() {
        return equilibrio;
    }
    /**
     * Reduce el Equilibrio actual del enemigo en una cantidad igual a la dada,
     * si el equilibrio se reduce a 0 o cae por debajo de 0 actualizará el valor de Vulnerable
     * y atrasará la acción del Enemigo.
     * @param reduccion Cantidad de Equilibrio perdido.
     */
    public void reducirEquilibrio(double reduccion) {
        equilibrio = equilibrio - reduccion;
        if (equilibrio <= 0) {
            aumentarVulnerabilidad();
            atrasarAccion(atrasoEquilibrioRoto());
        }
    }
    /**
     * Consigue el valor de Atraso de Acción cuando el equilibrio se reduce por debajo de 0.
     * @return Valor de Atraso de Acción.
     */
    public double atrasoEquilibrioRoto() {
        return 25.0;
    }
    /**
     * Restaura el Equilibrio a su valor máximo.
     */
    public void restaurarEquilibrio() {
        if (this.equilibrio <= 0) {
            equilibrio = equilibrioMax;
            restablecerVulnerabilidad();
        }
    }

    /**
     * Obtiene el multiplicador del estado actual de Vulnerabilidad del Enemigo.
     * @return Valor multiplicativo cuyo valor depende de la cantidad de Equilibrio del Enemigo.
     */
    public Vulnerabilidad getVulnerable() {
        return vul;
    }
    /**
     * Aumenta el valor del multiplicador de Vulnerable.
     */
    public void aumentarVulnerabilidad () {this.vul = Vulnerabilidad.SI;}
    /**
     * Reinicia el valor del multiplicador de Vulnerable.
     */
    public void restablecerVulnerabilidad () {this.vul = Vulnerabilidad.NO;}

    @Override
    public String toString() {
        return super.toString() + " | EQ: " + String.format("%.0f", equilibrio) + " }";
    }
}
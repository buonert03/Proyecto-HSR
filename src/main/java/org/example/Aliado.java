package org.example;

public class Aliado extends Entidad {
    private final double energiaMax;
    private double energia;

    // ---------------- CONSTRUCTOR ----------------
    public Aliado(String nombre, double psMax, double atqBase, double velBase, double energiaMax) {
        super(nombre, psMax, atqBase, velBase);
        this.energiaMax = energiaMax;
        this.energia = energiaMax/2;
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

    // ---------------- ENERGÍA ----------------
    /**
     * Consigue la Energía actual que posee el Aliado.
     * @return Cantidad de energía.
     */
    public double getEnergia() {
        return energia;
    }
    /**
     * Consigue el valor de Energía Máxima que puede tener el Aliado.
     * @return Cantidad de energía que puede guardar como máximo.
     */
    public double getEnergiaMax() {
        return energiaMax;
    }
    /**
     * Aumenta la cantidad de Energía que tiene el Aliado, pero no puede superar la Energía Máxima.
     * @param aumento Cantidad de Energía que gana.
     */
    public void aumentarEnergia(double aumento) {
        energia = energia + aumento;
        if (energia > energiaMax) {
            energia = energiaMax;
        }
    }
    /**
     * Reduce la cantidad de Energía que tiene el Aliado, pero no puede reducirse por debajo de cero.
     * @param reduccion Cantidad de Energía que pierde.
     */
    public void reducirEnergia(int reduccion) {
        energia = energia - reduccion;
        if (energia < 0) {
            energia = 0;
        }
    }
    /**
     * Reduce a cero la Energía del Aliado.
     */
    public void vaciarEnergia() {
        energia = 0;
    }

    @Override
    public String toString() {
        return super.toString() + " | ER: " + String.format("%.0f", energia) + " }";
    }
}

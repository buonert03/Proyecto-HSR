package org.example;

public abstract class Entidad {
    protected String nombre;
    protected double psMax;
    protected double psActual;
    protected double velBase;
    protected double velActual;
    protected double atqBase;
    protected double atqActual;
    protected double vaActual;

    public Entidad(String nombre, double psMax, double velBase, double atqBase) {
        this.nombre = nombre;
        this.psMax = psMax;
        this.psActual = psMax;
        this.velBase = velBase;
        this.velActual = velBase;
        this.atqBase = atqBase;
        this.atqActual = atqBase;
        this.vaActual = 1000/velActual;
    }

    abstract double getPsMax();
    abstract double getPsActual();
    abstract void perderPs();
    abstract void curarPs();

    abstract double getAtqBase();
    abstract double getAtqActual();
    abstract void reducirAtq();
    abstract void aumentarAtq();

    abstract double getVelBase();
    abstract double getVelActual();
    abstract void reducirVel();
    abstract void aumentarVel();

    abstract double getVaActual();
    abstract void resetearVa();
    abstract void avanzarVa();
    abstract void atrasarAccion();
    abstract void adelantarAccion();
}

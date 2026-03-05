package org.example;

public class Enemigo {
    private int psMax = 10500;
    private int psActual = psMax;
    private int velBase = 110;
    private int velActual = velBase;
    private int atqBase = 200;
    private int atqActual = atqBase;
    private int vaActual = (1000/velActual);
    private int equilibrio = 100;
    private int vulnerable = 0;

    public int getPsMax() {
        return psMax;
    }
    public int getPsActual() {
        return psActual;
    }
    public int getVelBase() {
        return velBase;
    }
    public int getVelActual() {
        return velActual;
    }
    public int getAtqBase() {
        return atqBase;
    }
    public int getAtqActual() {
        return atqActual;
    }
    public int getVaActual() {
        return vaActual;
    }
    public int getEquilibrio() {
        return equilibrio;
    }
    public int getVulnerable() {
        return vulnerable;
    }
}
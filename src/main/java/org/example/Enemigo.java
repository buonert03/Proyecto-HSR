package org.example;

public class Enemigo {
    private String nombre;
    private double psMax = 10500;
    private double psActual = psMax;
    private double velBase = 110;
    private double velActual = velBase;
    private double atqBase = 200;
    private double atqActual = atqBase;
    private double vaActual = (1000/velActual);
    private double equilibrioMax = 100;
    private double equilibrio = equilibrioMax;
    private double vulnerable = 1;

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
    public String getNombre () {return nombre;}

    public double getPsMax() {
        return psMax;
    }
    public double getPsActual() {
        return psActual;
    }
    public void perderPs(double danoRecibido) {
        psActual = psActual - danoRecibido;
    }
    public void curarPs(double curaRecibida) {
        psActual = psActual + curaRecibida;
        if (psActual > psMax) {psActual = psMax;}}

    public double getVelBase() {
        return velBase;
    }
    public double getVelActual() {
        return velActual;
    }
    public void aumentarVel(double aumento) {
        velActual = velActual + (velBase*aumento);}
    public void reducirVel(double reduccion) {
        velActual = velActual - (velBase*reduccion);}

    public double getAtqBase() {
        return atqBase;
    }
    public double getAtqActual() {
        return atqActual;
    }
    public void aumentarAtq(double aumento) {
        atqActual = atqActual + (atqBase*(1+aumento));}
    public void reducirAtq(double reduccion) {
        atqActual = atqActual - (atqBase*(1+reduccion));}

    public double getVaActual() {
        return vaActual;
    }
    public void avanzarVa() {vaActual = vaActual - 1;}
    public void resetearVa() {vaActual = (1000/velActual);}
    public void adelantarAccion(double adelanto) {
        vaActual = vaActual - (1000/velActual)*(1+adelanto);}
    public void atrasarAccion(double atraso) {
        vaActual = vaActual + (1000/velActual)*(1+atraso);}

    public double getEquilibrioMax() {
        return equilibrioMax;
    }
    public double getEquilibrio() {
        return equilibrio;
    }
    public void reducirEquilibrio(double reduccion) {
        equilibrio = equilibrio - reduccion;
    }
    public void restaurarEquilibrio() {
        equilibrio = equilibrioMax;
    }
    public double getVulnerable() {
        return vulnerable;
    }
    public void aumentarVulnerabilidad () {vulnerable = 1.3;}
    public void restablecerVulnerabilidad () {vulnerable = 1;}
}
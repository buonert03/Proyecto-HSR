package org.example;

public class Enemigo {
    private String nombre;
    private double psMax = 10500;
    private double psActual = psMax;
    private double velBase = 180;
    private double velActual = velBase;
    private double atqBase = 150;
    private double atqActual = atqBase;
    private double vaActual = (1000/velActual);
    private double equilibrioMax = 100;
    private double equilibrio = equilibrioMax;
    private double vulnerable = 1;

    // Nombre
    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
    public String getNombre () {return nombre;}

    // Vida
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

    // Ataque
    public double getAtqBase() {
        return atqBase;
    }
    public double getAtqActual() {
        return atqActual;
    }
    public void aumentarAtq(double aumento) {
        atqActual = atqActual + (atqBase*(aumento/100));}
    public void reducirAtq(double reduccion) {
        atqActual = atqActual - (atqBase*(reduccion/100));}

    // Velocidad y Valor de Acción
    public double getVelBase() {
        return velBase;
    }
    public double getVelActual() {
        return velActual;
    }
    public void aumentarVel(double aumento) {
        Double velAumentada = velActual + (velBase * (aumento/100));
        this.vaActual = this.vaActual - ((1000/velActual)-(1000/velAumentada));
        this.velActual = velAumentada;
    }
    public void reducirVel(double reduccion) {
        Double velReducida = velActual - (velBase * (reduccion/100));
        if (velReducida < velBase/2) {velReducida = velBase/2;}
        this.vaActual = this.vaActual + ((1000/velReducida)-(1000/this.velActual));
        this.velActual = velReducida;
    }

    public double getVaActual() {
        return vaActual;
    }
    public void avanzarVa() {
        vaActual = vaActual - 1;
        if (vaActual < 0) {vaActual = 0;}
    }
    public void resetearVa() {vaActual = (1000/velActual);}
    public void adelantarAccion(double adelanto) {
        vaActual = vaActual - (1000/velActual)*(adelanto/100);}
    public void atrasarAccion(double atraso) {
        vaActual = vaActual + (1000/velActual)*(atraso/100);}

    // Equilibrio
    public double getEquilibrioMax() {
        return equilibrioMax;
    }
    public double getEquilibrio() {
        return equilibrio;
    }
    public void reducirEquilibrio(double reduccion) {
        equilibrio = equilibrio - reduccion;
        if (equilibrio <= 0) {
            aumentarVulnerabilidad();
            atrasarAccion(25);
        }
    }
    public void restaurarEquilibrio() {
        if (this.equilibrio <= 0) {
            equilibrio = equilibrioMax;
            restablecerVulnerabilidad();
        }
    }
    public double getVulnerable() {
        return vulnerable;
    }
    public void aumentarVulnerabilidad () {vulnerable = 1.3;}
    public void restablecerVulnerabilidad () {vulnerable = 1;}


}
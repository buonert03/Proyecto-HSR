package org.example;

public class Aliado {
    private String nombre;
    private double psMax = 1000;
    private double psActual = psMax;
    private double velBase = 100;
    private double velActual = velBase;
    private double atqBase = 500;
    private double atqActual = atqBase;
    private int energiaMax = 150;
    private int energia = 75;
    private double vaActual = (1000 / velActual);
// va = Valor de Acción, los puntos de movimiento restantes hasta su próximo turno

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPsMax() {
        return psMax;
    }

    public double getPsActual() {
        return psActual;
    }

    public void perderPs(int danoRecibido) {
        psActual = psActual - danoRecibido;
    }

    public void curarPs(int curaRecibida) {
        psActual = psActual + curaRecibida;
        if (psActual > psMax) {
            psActual = psMax;
        }
    }

    public double getVelBase() {
        return velBase;
    }

    public double getVelActual() {
        return velActual;
    }

    public void aumentarVel(double aumento) {
        velActual = velActual + (velBase * (aumento/100));
    }

    public void reducirVel(double reduccion) {
        velActual = velActual - (velBase * (reduccion/100));
    }

    public double getAtqBase() {
        return atqBase;
    }

    public double getAtqActual() {
        return atqActual;
    }

    public void aumentarAtq(double aumento) {
        atqActual = atqActual + (atqBase * (aumento/100));
    }

    public void reducirAtq(double reduccion) {
        atqActual = atqActual - (atqBase * (reduccion/100));
    }

    public int getEnergia() {
        return energia;
    }

    public void aumentarEnergia(int aumento) {
        energia = energia + aumento;
        if (energia > energiaMax) {
            energia = energiaMax;
        }
    }

    public void reducirEnergia(int reduccion) {
        energia = energia - reduccion;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void vaciarEnergia() {
        energia = 0;
    }

    public double getVaActual() {
        return vaActual;
    }

    public void resetearVa() {
        vaActual = (1000 / velActual);
    }

    public void adelantarAccion(double adelanto) {
        vaActual = vaActual - (1000 / velActual) * (adelanto/100);
    }

    public void atrasarAccion(double atraso) {
        vaActual = vaActual + (1000 / velActual) * (atraso/100);
    }

    @Override
    public String toString() {
        return "Aliado{" +
                "psMax=" + psMax +
                ", psActual=" + psActual +
                ", velBase=" + velBase +
                ", velActual=" + velActual +
                ", atqBase=" + atqBase +
                ", atqActual=" + atqActual +
                ", energiaMax=" + energiaMax +
                ", energia=" + energia +
                ", vaActual=" + vaActual +
                '}';
    }
}

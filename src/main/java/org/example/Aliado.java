package org.example;

public class Aliado {
    private double psMax = 1000;
    private double psActual = psMax;
    private int velBase = 100;
    private int velActual = velBase;
    private int atqBase = 500;
    private int atqActual = atqBase;
    private int energiaMax = 150;
    private int energia = 75;
    private double vaActual = (1000/velActual);
    private int phMax = 5;
    private int ph = 3;
    private double danoInfligido = 0;
    private int equilibrioReducido = 0;
    private double velReducida = 0.0;
// va = Valor de Acción, los puntos de movimiento restantes hasta su próximo turno
// ph = Puntos de Habilidad, puntos que se consumen o regeneran al usar ciertas habilidades

    public double getPsMax() {
        return psMax;
    }
    public double getPsActual() {
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
    public int getEnergia() {
        return energia;
    }
    public void ataqueNormal(int opcion) {
        if (opcion == 1) {
            energia = energia + 25;
            if (energia > energiaMax) {energia = energiaMax;}
            danoInfligido = atqActual * 1.25;
            equilibrioReducido = 20;
            if (ph < phMax) {ph = ph +1;}
            vaActual = (1000/velActual);
        }
    }
    public void ataqueEspecial(int opcion) {
        if ((opcion == 2) && (ph > 0)){
            ph = ph -1;
            energia = energia + 50;
            if (energia > energiaMax) {energia = energiaMax;}
            danoInfligido = atqActual * 2;
            equilibrioReducido = 30;
            velReducida = 0.2;
            vaActual = (1000/velActual);
        }
    }
    public void ataqueDefinitivo(int opcion) {
        if ((opcion == 3) && (energia == 150)) {
            energia = 0;
            danoInfligido = atqActual * 4;
            vaActual = ((1000/velActual)*(1-0.2));
        }
    }
    public void perderPs(int danoRecibido) {
        psActual = psActual - danoRecibido;
    }
    public void cura(int opcion) {
        if ((opcion == 4) &&(ph > 0)) {
            psActual = psActual + psMax*0.25;
            if (psActual > psMax) {
                psActual = psMax;
            }
            energia = energia + 25;
            if (energia > energiaMax) {energia = energiaMax;}
        }
    }
    public void bufo(int opcion) {

    }
}

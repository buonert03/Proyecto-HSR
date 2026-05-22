package org.example;


public abstract class Entidad {
    protected String nombre;
    protected double psMax;
    protected double psActual;
    protected double atqBase;
    protected double atqActual;
    protected double velBase;
    protected double velActual;
    protected double vaActual;

    /**
     * Constructor de la clase Entidad.
     * @param nombre Nombre de la Entidad.
     * @param psMax Valor de Puntos de Salud Máxima, psActual tendrá el mismo valor al inicio.
     * @param velBase Valor de Velocidad Base, velActual tendrá el mismo valor al inicio,
     *                y vaActual será igual a dividir 1000 entre este valor.
     * @param atqBase Valor de Ataque Base, atqActual tendrá el mismo valor al inicio.
     */
    public Entidad(String nombre, double psMax, double atqBase, double velBase) {
        this.nombre = nombre;
        this.psMax = psMax;
        this.psActual = psMax;
        this.atqBase = atqBase;
        this.atqActual = atqBase;
        this.velBase = velBase;
        this.velActual = velBase;
        this.vaActual = 1000/velActual;
    }

    // ---------------- NOMBRE ----------------
    /**
     * Método para conseguir el nombre de la entidad.
     * @return El nombre de la entidad.
     */
    protected String getNombre() { return nombre;}

    // ---------------- SALUD ----------------
    /**
     * Método para conseguir el valor de la Salud máxima que puede tener la entidad.
     * @return Puntos de Salud máxima alcanzable por la entidad.
     */
    protected double getPsMax()  {
        return psMax;
    }
    /**
     * Método para conseguir el valor de la Salud actual que tiene la entidad.
     * @return Puntos de Salud que posee la entidad.
     */
    protected double getPsActual()  {
        return psActual;
    }
    /**
     * Método para reducir los Puntos de Salud de la entidad en un valor equivalente al daño recibido.
     * @param danoRecibido Valor que reduce los Puntos de Salud de la entidad.
     */
    protected void perderPs(double danoRecibido)  {
        psActual = psActual - danoRecibido;
    }
    /**
     * Suma a los PS actuales un valor igual al porcentaje de curación dado respecto a la salud máxima, pero que no podrá aumentos sus PS por encima de los PS-Max.
     * @param curaPorcentaje Porcentaje de la salud máxima que se usará para curar a la Entidad.
     */
    protected void curarPs(double curaPorcentaje) {
        psActual = psActual + (psMax * (curaPorcentaje/100));
        if (psActual > psMax) {
            psActual = psMax;
        }
    }

    // ---------------- ATAQUE ----------------
    /**
     * Método para conseguir el valor de Ataque Base de la Entidad.
     * @return El valor de Ataque Base de la Entidad.
     */
    protected double getAtqBase()  {
        return atqBase;
    }
    /**
     * Método para conseguir el valor de Ataque Actual de la Entidad.
     * @return El valor de Ataque Actual de la Entidad.
     */
    protected double getAtqActual()  {
        return atqActual;
    }
    /**
     * Reduce el ataque actual de la Entidad dado al porcentaje de reducción sobre el ataque base,
     * el ataque actual nunca podrá ser menor a la mitad del ataque base.
     * @param reduccion Porcentaje del Ataque Base que se usará para reducir el ataque actual de la Entidad.
     */
    protected void reducirAtq(double reduccion)  {
        double ataqueReducido = atqActual - (atqBase * (reduccion/100));
        if (ataqueReducido < atqBase/2) {
            ataqueReducido = atqBase/2;
        }
        atqActual = ataqueReducido;
    }
    /**
     * Aumenta el ataque actual de la Entidad dado el porcentaje de aumento sobre el ataque base.
     * @param aumento Porcentaje del Ataque Base que se usará para aumentar el ataque actual de la Entidad.
     */
    protected void aumentarAtq(double aumento)  {
        atqActual = atqActual + (atqBase * (aumento/100));
    }

    // ---------------- VELOCIDAD ----------------
    /**
     * Consigue la Velocidad Base de la Entidad.
     * @return El valor de la Velocidad Base de la Entidad.
     */
    protected double getVelBase()  {
        return velBase;
    }
    /**
     * Consigue la Velocidad Actual de la Entidad.
     * @return El valor de la Velocidad Actual de la Entidad.
     */
    protected double getVelActual()  {
        return velActual;
    }
    /**
     * Reduce la Velocidad Actual de la Entidad dado un porcentaje de reducción sobre la Velocidad Base,
     * la Velocidad Actual nunca podrá ser inferior a la mitad de la Velocidad Base.
     * @param reduccion Porcentaje de la Velocidad Base que se usará para reducir la Velocidad Actual.
     */
    protected void reducirVel(double reduccion) {
        double velReducida = velActual - (velBase * (reduccion/100));
        if (velReducida < velBase/2) {velReducida = velBase/2;}
        this.vaActual = this.vaActual + ((1000/velReducida)-(1000/this.velActual));
        this.velActual = velReducida;
    }
    /**
     * Aumenta la Velocidad Actual de la Entidad dado un porcentaje de reducción sobre la Velocidad Base.
     * @param aumento Porcentaje de la Velocidad Base que se usará para aumentar la Velocidad Actual.
     */
    protected void aumentarVel(double aumento) {
        double velAumentada = velActual + (velBase * (aumento/100));
        this.vaActual = this.vaActual - ((1000/velActual)-(1000/velAumentada));
        this.velActual = velAumentada;
    }

    // ---------------- VALOR DE ACCIÓN ----------------
    /**
     * Consigue el Valor de Acción actual de la Entidad.
     * @return El Valor de Acción
     */
    protected double getVaActual()  {
        return vaActual;
    }
    /**
     * Reinicia el Valor de Acción de la Entidad, para ello toma en cuenta la Velocidad Actual.
     */
    protected void resetearVa() {
        this.vaActual = (1000 / velActual);
    }
    /**
     * Avanza en una unidad el Valor de Acción.
     */
    protected void avanzarVa() {
        this.vaActual = vaActual - 1;
        if (vaActual < 0) {vaActual = 0;}
    }
    /**
     * Adelanta la siguiente acción de la Entidad al reducir su Valor de Acción.
     * @param adelanto Porcentaje de adelanto.
     */
    protected void atrasarAccion(double adelanto)  {
        vaActual = vaActual + (1000 / velActual) * (adelanto/100);
    }
    /**
     * Atrasa la siguiente acción de la Entidad al aumentar su Valor de Acción.
     * @param atraso Porcentaje de atraso.
     */
    protected void adelantarAccion(double atraso)  {
        vaActual = vaActual - (1000 / velActual) * (atraso/100);
    }

    @Override
    public String toString() {
        return nombre +
                " { PS: " + String.format("%.0f", psActual) +
                " | ATQ: " + String.format("%.0f", atqActual) +
                " | VEL: " + String.format("%.0f", velActual) +
                " | VA: " + String.format("%.2f", vaActual);
    }
}


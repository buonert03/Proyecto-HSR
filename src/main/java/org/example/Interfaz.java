package org.example;

public class Interfaz {

    public void situacion (Aliado aliado, Enemigo enemigo, double ph) {
        System.out.println();
        System.out.printf("Aliado: %.2f PS, %.2f ATQ, %.2f VEL, %.2f ER, %.2f VA",
                aliado.getPsActual(), aliado.getAtqActual(), aliado.getVelActual(),
                aliado.getEnergia(), aliado.getVaActual());
        System.out.println();
        System.out.printf("Enemigo: %.2f PS, %.2f ATQ, %.2f VEL, %.2f EQ, %.2f VA",
                enemigo.getPsActual(), enemigo.getAtqActual(), enemigo.getVelActual(),
                enemigo.getEquilibrio(), enemigo.getVaActual());
        System.out.println();
        System.out.printf("%.0f PH", ph);
        System.out.println();
        System.out.println();
    }

    public void seleccionarHabilidad() {
        System.out.println("Escoge el número de la habilidad que quieras usar.");
        System.out.println("Básico: 1 | Incremento: 2 | Curación: 3 | Especial: 4 | Definitiva: 5");
    }

    public void noUsable() {
        System.out.println("No puede activarse la habilidad");
    }
    public void quieresUsarLa(int habilidad) {
        System.out.println();
        System.out.println("Escribe 'S' si quieres usar la habilidad " + habilidad);
    }
    public void cancelar() {
        System.out.println("Cancelaste el uso de la habilidad");
    }

    public void basico(Aliado aliado, Enemigo enemigo, double ph){
        if (ph == 5) {ph = 4;}

        double energiaActual = aliado.getEnergia();
        double energia = 25.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        double eq = enemigo.getEquilibrio();
        double reducirEq = 20;
        double eqReducido = eq - reducirEq;
        if (eqReducido < 0) {
            eqReducido = 0;
        }
        double vulnerable = enemigo.getVulnerable();
        if (vulnerable == 1 || eqReducido == 0) {
            vulnerable = 1.3;
        }

        double multiplicador = 1.25;

        System.out.println("Efectos de la habilidad:");
        System.out.printf("Equilibrio Enemigo: %.2f -> %.2f | Daño: %.2f | ER: %.2f |PH: %.2f | VA: %.2f",
                enemigo.getEquilibrio(), eqReducido,
                enemigo.getAtqActual()*multiplicador*vulnerable,
                energiaFinal, ph+1, 1000/aliado.getVelActual());
    }

    public void incremento(Aliado aliado, double ph) {
        double energiaActual = aliado.getEnergia();
        double energia = 75.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        System.out.println("Efectos de la habilidad:");
        System.out.printf("Ataque: %.2f -> %.2f | Velocidad: %.2f -> %.2f | Energía: %.2f | PH: %.2f | VA: %.2f",
                aliado.getAtqActual(), aliado.getAtqActual()+aliado.getAtqBase()*0.4,
                aliado.getVelActual(), aliado.getVelActual()+ aliado.getVelActual()*0.3,
                energiaFinal, ph-2, (1000/(aliado.getVelActual()+aliado.getVelBase()*0.3))*0.5);
    }

    public void curacion(Aliado aliado, double ph) {
        double energiaActual = aliado.getEnergia();
        double energia = 25.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        double vidaCurada = aliado.getPsActual() + aliado.getPsMax()*0.25;
        if (vidaCurada > aliado.getPsMax()) {
            vidaCurada = aliado.getPsMax();
        }
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Puntos de Salud: %.2f -> %.2f | Energía: %.2f | PH: %.2f | VA: %.2f",
                aliado.getPsActual(), vidaCurada,
                energiaFinal, ph, 1000/aliado.getVelActual());
    }

    public void especial(Aliado aliado, Enemigo enemigo, double ph) {
        double energiaActual = aliado.getEnergia();
        double energia = 50.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        double eq = enemigo.getEquilibrio();
        double reducirEq = 40;
        double eqReducido = eq - reducirEq;
        if (eqReducido < 0) {
            eqReducido = 0;
        }
        double vulnerable = enemigo.getVulnerable();
        if (vulnerable == 1 || eqReducido == 0) {
            vulnerable = 1.3;
        }

        double multiplicador = 2;

        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Postura: %.2f -> %.2f | Vel: %.2f -> %.2f | VA: %.2f }",
                enemigo.getEquilibrio(), enemigo.getEquilibrio()-40,
                enemigo.getVelActual(), enemigo.getVelActual()-enemigo.getVelBase()*0.2,
                enemigo.getVaActual()+((1000/(enemigo.getVelActual()-enemigo.getVelBase()*0.2))-1000/enemigo.getVelActual()));
        System.out.println();
        System.out.printf("Aliado { Daño: %.2f | ER: %.2f | PH %.2f | VA: %.2f }",
               aliado.getAtqActual()*multiplicador*vulnerable, energiaFinal,
                ph-1, 1000/(aliado.getVelActual()));
    }

    public void definitiva(Aliado aliado, Enemigo enemigo, double ph) {
        if (ph>2) {ph = 2;}

        double eq = enemigo.getEquilibrio();
        double reducirEq = 40;
        double eqReducido = eq - reducirEq;
        if (eqReducido < 0) {
            eqReducido = 0;
        }
        double vulnerable = enemigo.getVulnerable();
        if (vulnerable == 1 || eqReducido == 0) {
            vulnerable = 1.3;
        }

        double multiplicador = 2;

        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Equilibrio: %.2f -> %.2f }", enemigo.getEquilibrio(), enemigo.getEquilibrio()-50);
        System.out.println();
        System.out.printf("Aliado: { Daño: %.2f | ER: 0 | PH %.2f | VA: %.2f }",
                aliado.getAtqActual() * multiplicador * vulnerable,
                ph+3, 1000/aliado.getVelActual());
    }

    public void enemigoBasico(Aliado aliado, Enemigo enemigo) {
        System.out.printf("Enemigo usó el ataque básico y te hizo %.2f puntos de daño y " +
                        "su Valor de Acción volvió a %.2f",
                enemigo.getAtqActual()*1.2, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.println();
    }

    public void enemigoQuitar(Aliado aliado, Enemigo enemigo, double ph) {
        if (ph==0) {ph = 1;}
        System.out.printf("Enemigo redujó los PH a %.2f, te hizo %.2f puntos de daño y " +
                        "su Valor de Acción volvió a %.2f",
                ph-1, enemigo.getAtqActual()*1.4, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.println();
    }

    public void enemigoAutoBufo(Aliado aliado, Enemigo enemigo) {
        System.out.printf("Enemigo mejoró su ataque de %.2f a %.2f y su vel de %.2f a %.2f," +
                        " te hizo %.2f puntos de daño y su Valor de Acción volvió a %.2f",
                enemigo.getAtqActual(), enemigo.getAtqActual()*1.15,
                enemigo.getVelActual(), enemigo.getVelActual()+enemigo.getVelBase()*0.15,
                enemigo.getAtqActual()*1.15*1.5,
                1000/(enemigo.getVelActual()+enemigo.getVelBase()*0.15));
        System.out.println();
        System.out.println();
    }

    public void enemigoDebufo(Aliado aliado, Enemigo enemigo) {
        Double energiaReducida = aliado.getEnergia()-10;
        if (energiaReducida < 0) {
            energiaReducida = 0.0;
        }
        System.out.printf("Enemigo redujo tu ataque de %.2f a %.2f, tu velocidad de %.2f a %.2f, y tu energía de %.2f a %.2f," +
                " te hizo %.2f puntos de daño y su Valor de Acción volvió a %.2f",
                aliado.getAtqActual(), aliado.getAtqActual()-aliado.getAtqBase()*0.1,
                aliado.getVelActual(), aliado.getVelActual()- aliado.getVelBase()*0.1,
                aliado.getEnergia(), energiaReducida,
                enemigo.getAtqActual()*1.1, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.println();
    }

    public void enemigoDefinitiva(Aliado aliado, Enemigo enemigo) {
        Double vidaCurada = enemigo.getPsActual() + enemigo.getPsMax()*0.15;
        if (vidaCurada > enemigo.getPsMax()) {
            vidaCurada = enemigo.getPsMax();
        }
        System.out.printf("Enemigo se curó hasta tener Puntos de Salud %.2f, " +
                " te hizo %.2f puntos de daño y atrasó tu acción hasta %.2f." +
                " Su acción volvió a %.2f",
                vidaCurada, enemigo.getAtqActual()*2,
                aliado.getVaActual()+(1000/aliado.getVelActual())*0.2,
                1000/enemigo.getVelActual());
        System.out.println();
        System.out.println();
    }
}

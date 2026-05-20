package org.example;

public class Interfaz {

    // Interfaz general
    public void situacion (Aliado aliado, Enemigo enemigo, double ph) {
        System.out.println(aliado);
        System.out.println(enemigo);
        System.out.printf("%.0f PH", ph);
        System.out.println();
        System.out.println();
    }
    public void seleccionarHabilidad() {
        System.out.println("Escoge el número de la habilidad que quieras usar");
        System.out.println("Básico: 1 | Incremento: 2 | Curación: 3 | Especial: 4 | Definitiva: 5");
    }
    public void noUsable(Double ph) {
        System.out.println("No puede activarse la habilidad, necesitas " + ph + " PH para poder usarla");
        System.out.println();
    }
    public void noEnergia(Double energiaMax, Double energia) {
        System.out.println("No puede activarse la habilidad, necesitas " + energiaMax + " puntos de energía, pero solo tienes " + energia);
        System.out.println();
    }
    public void quieresUsarLa(int habilidad) {
        System.out.println();
        System.out.println("Escribe 'S' si quieres usar la habilidad " + habilidad);
    }
    public void cancelar() {
        System.out.println("Cancelaste el uso de la habilidad");
    }

    // -------------------- HABILIDADES ALIADO --------------------
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
        Double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }

        double multiplicador = 1.25;
        Double daño = aliado.getAtqActual()*multiplicador*vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { EQ: %.0f -> %.0f | PS: %.0f -> %.0f | VA: %.2f }",
                enemigo.getEquilibrio(), eqReducido, enemigo.getPsActual(),
                enemigo.getPsActual()-daño, va);
        System.out.println();
        System.out.printf("Aliado { Daño: %.0f | ER: %.0f -> %.0f | VA: %.2f }",
                daño, aliado.getEnergia(), energiaFinal, 1000/aliado.getVelActual());
        System.out.println();
        System.out.printf("PH: %.0f", ph+1);
        System.out.println();
    }

    public void incremento(Aliado aliado, double ph) {
        double energiaActual = aliado.getEnergia();
        double energia = 75.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Aliado { Energía: %.0f -> %.0f | Ataque: %.0f -> %.0f | Velocidad: %.2f -> %.2f | VA: %.2f }",
                aliado.getEnergia(), energiaFinal,
                aliado.getAtqActual(), aliado.getAtqActual()+aliado.getAtqBase()*0.4,
                aliado.getVelActual(), aliado.getVelActual()+aliado.getVelActual()*0.3,
                (1000/(aliado.getVelActual()+aliado.getVelBase()*0.3))*0.5);
        System.out.println();
        System.out.printf("PH %.0f", ph-2);
        System.out.println();
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

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Aliado { Energía: %.0f -> %.0f | Puntos de Salud: %.0f -> %.0f | VA: %.2f }",
                aliado.getEnergia(), energiaFinal,
                aliado.getPsActual(), vidaCurada,
                1000/aliado.getVelActual());
        System.out.println();
        System.out.printf("PH %.0f", ph);
        System.out.println();
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
        Double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }

        Double vel = enemigo.getVelActual() - (enemigo.getVelBase()*0.2);
        if (vel < enemigo.getVelBase()/2) {
            vel = enemigo.getVelBase()/2;
        }

        va = va + ((1000/vel)-(1000/enemigo.getVelActual()));
        double multiplicador = 2;
        double daño = aliado.getAtqActual()*multiplicador*vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { EQ: %.0f -> %.0f | PS: %.0f -> %.0f | Vel: %.2f -> %.2f | VA: %.2f }",
                enemigo.getEquilibrio(), enemigo.getEquilibrio()-40, enemigo.getPsActual(), enemigo.getPsActual()-daño,
                enemigo.getVelActual(), vel, va);
        System.out.println();
        System.out.printf("Aliado { Daño: %.0f | ER: %.0f -> %.0f | VA: %.2f }",
                daño, aliado.getEnergia(), energiaFinal,
                1000/(aliado.getVelActual()));
        System.out.println();
        System.out.printf("PH %.2f", ph-1);
        System.out.println();
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
        Double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }
        double multiplicador = 2;
        double daño = aliado.getAtqActual() * multiplicador * vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Equilibrio: %.2f -> %.2f | PS: %.2f -> %.2f | VA: %.2f }",
                enemigo.getEquilibrio(), enemigo.getEquilibrio()-50,
                enemigo.getPsActual(), enemigo.getPsActual() - daño,
                va);
        System.out.println();
        System.out.printf("Aliado: { Daño infligido: %.2f | ER: 0 | VA: %.2f }",
                daño,
                1000/aliado.getVelActual());
        System.out.println();
        System.out.printf("PH %.2f", ph+3);
        System.out.println();
    }

    // -------------------- HABILIDADES ENEMIGO --------------------
    public void enemigoBasico(Aliado aliado, Enemigo enemigo) {
        Double multiplicador = 1.2;
        Double daño = enemigo.getAtqActual()*multiplicador;

        System.out.println("ENEMIGO USÓ EL ATAQUE BÁSICO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Daño infligido: %.2f | VA: %.2f }",
                daño, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf("Aliado { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - daño);
        System.out.println();
        System.out.println();
    }

    public void enemigoQuitar(Aliado aliado, Enemigo enemigo, double ph) {
        if (ph==0) {ph = 1;}

        Double multiplicador = 1.4;
        Double daño = enemigo.getAtqActual()*multiplicador;
        System.out.println("ENEMIGO USÓ EL ATAQUE QUITAR PH");
        System.out.println("Efectos de la habilidad: ");
        System.out.printf("Enemigo { Daño infligido: %.2f | VA: %.2f",
                daño, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf("Aliado { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - daño);
        System.out.println();
        System.out.printf("PH: %.0f", ph - 1);
        System.out.println();
        System.out.println();
    }

    public void enemigoAutoBufo(Aliado aliado, Enemigo enemigo) {
        Double ataque = enemigo.getAtqActual()+enemigo.getAtqBase()*0.15;
        Double multiplicador = 1.5;
        Double daño = ataque*multiplicador;

        System.out.println("ENEMIGO USÓ EL ATAQUE BUFO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Ataque: %.2f -> %.2f | Daño infligido: %.2f | Velocidad: %.2f -> %.2f | VA: %.2f",
                enemigo.getAtqActual(), ataque,daño,
                enemigo.getVelActual(), enemigo.getVelActual()+enemigo.getVelBase()*0.15, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf("Aliado { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - daño);
        System.out.println();
        System.out.println();
    }

    public void enemigoDebufo(Aliado aliado, Enemigo enemigo) {
        Double energiaReducida = aliado.getEnergia()-10;
        if (energiaReducida < 0) {
            energiaReducida = 0.0;
        }
        Double multiplicador = 1.5;
        Double daño = enemigo.getAtqActual()*multiplicador;

        System.out.println("ENEMIGO USÓ EL ATAQUE DEBUFO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Daño infligido: %.2f | VA: %.2f",
                daño, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf("Aliado { PS: %.2f -> %.2f | Ataque: %.2f -> %.2f | Velocidad: %.2f -> %.2f | Energía: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - daño,
                aliado.getAtqActual(), aliado.getAtqActual()-aliado.getAtqBase()*0.1,
                aliado.getVelActual(), aliado.getVelActual()-aliado.getVelBase()*0.1,
                aliado.getEnergia(), energiaReducida);
        System.out.println();
        System.out.println();
    }

    public void enemigoDefinitiva(Aliado aliado, Enemigo enemigo) {
        Double vidaCurada = enemigo.getPsActual() + enemigo.getPsMax()*0.15;
        if (vidaCurada > enemigo.getPsMax()) {
            vidaCurada = enemigo.getPsMax();
        }

        Double adelanto = 40.0;
        Double atraso = 20.0;
        Double multiplicador = 1.5;
        Double daño = enemigo.getAtqActual()*multiplicador;

        System.out.println("ENEMIGO USÓ EL ATAQUE DEFINITIVO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf("Enemigo { Daño infligido: %.2f | PS: %.2f -> %.2f | VA: %.2f",
                daño, enemigo.getPsActual(), vidaCurada, 1000/enemigo.getVelActual()*(1-adelanto/100));
        System.out.println();
        System.out.printf("Aliado { PS: %.2f -> %.2f | VA: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - daño,
                aliado.getVaActual(), aliado.getVaActual()+(1000/aliado.getVelActual())*(atraso/100));
        System.out.println();
        System.out.println();
    }
}

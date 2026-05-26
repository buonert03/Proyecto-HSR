package org.example;

public class Interfaz {

    // Interfaz general
    public void tutorial() {
        System.out.println("----- TUTORIAL -----");
        System.out.println("Tu objetivo para ganar este combate será reducir los Puntos de Salud del enemigo a 0");
        System.out.println("Para ello tendrás que usar una variedad de habilidades y gestionar diferentes recursos de combate, solo podrás usar habilidades durante tu turno, y al usar una deberás esperar a tu próximo turno otra vez");
        System.out.println();
        System.out.println("ESTADÍSTICAS");
        System.out.println("PS: Puntos de Salud, si tus PS llegan a cero pierdes ");
        System.out.println("ATQ: Ataque, este valor se tomará en cuenta a la hora de reducir los PS del objetivo");
        System.out.println("VA: Valor de Acción, este valor indica cuanto te falta para tomar tu próximo turno, si llega a 0 será tu turno");
        System.out.println("VEL: Velocidad, este valor define cuanto VA tendrás al inicio y después de cada turno");
        System.out.println("ER: Energía, este valor es exclusivo del Aliado, y es un recurso que cuando alcance su máximo podrás consumirlo para usar una habilidad definitiva, recuperas ER con cualquier habilidad menos con la Definitiva");
        System.out.println("EQ: Equilibrio, este valor es exclusivo del Enemigo, lo podrás reducir con cada ataque que le inflingas, y si cae a cero o menos, el Enemigo recibirá más daño y se atrasará su VA, solo podrá recuperar EQ al inicio de su turno después de caer por debajo de 0");
        System.out.println("PH: Puntos de Habilidad, recurso global que se genera o se consume al usar diferentes habilidades, nunca puede estar por debajo de 0 ni superar el límite máximo");
        System.out.println();
        System.out.println("HABILIDADES DEL ALIADO");
        System.out.println("Habilidad 1, Básico: Haces un poco de daño, y recuperas un PH");
        System.out.println("Habilidad 2, Incremento: Consume 2 PH, recupera mucha ER, aumenta el ATQ y la VEL, y adelanta tu próxima acción");
        System.out.println("Habilidad 3, Curación: Requiere 1 PH para usarse pero no lo consume, regenera PS en base a la cantidad máxima de PS que puedes tener");
        System.out.println("Habilidad 4, Especial: Consume 1 PH, reduce el ATQ y la VEL del Enemigo y le hace una cantidad de daño considerable");
        System.out.println("Habilidad 5, Definitiva: Consume toda la ER, regenera 3 PH, hace daño masivo y adelanta un poco tu próxima acción");
        System.out.println();
        System.out.println("HABILIDADES DEL ENEMIGO");
        System.out.println("Habilidad 1, Básico: Hace un poco daño");
        System.out.println("Habilidad 2, QuitarPH: Hace algo de daño y reduce la cantidad de PH si hubiese");
        System.out.println("Habilidad 3, Autobufo: Hace bastante daño e incrementa su ATQ y VEL");
        System.out.println("Habilidad 4, Debuff: Hace muy poco daño y reduce el ATQ, la VEL y la ER del Aliado");
        System.out.println("Habilidad 5, Definitiva: Hace mucho daño, se cura PS en base a sus PS Máximos, adelanta su próxima acción y atrasa un poco la acción del Aliado");
        System.out.println();
    }
    public void situacion (Aliado aliado, Enemigo enemigo, double ph, double phmax) {
        System.out.println(enemigo);
        System.out.println(aliado);
        System.out.printf("%.0f/%.0f PH", ph, phmax);
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
    public void quieresUsarLa(String habilidad) {
        System.out.println();
        System.out.println("Escribe 'S' si quieres usar la habilidad " + habilidad);
    }
    public void cancelar() {
        System.out.println("Cancelaste el uso de la habilidad");
    }

    // -------------------- HABILIDADES ALIADO --------------------
    public void basico(Aliado aliado, Enemigo enemigo, double ph, double phmax){
        ph += 1;
        if (ph > phmax) {ph = phmax;}

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

        double vulnerable = enemigo.getVulnerable().getVulnerable();
        if (vulnerable == 1 && eqReducido == 0) {
            vulnerable = 1.3;
        }
        double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }

        double multiplicador = 1.25;
        double damage = aliado.getAtqActual()*multiplicador*vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { EQ: %.0f -> %.0f | PS: %.0f -> %.0f | VA: %.2f }",
                enemigo.getEquilibrio(), eqReducido, enemigo.getPsActual(),
                enemigo.getPsActual()- damage, va);
        System.out.println();
        System.out.printf(aliado.getNombre() + " { Daño: %.0f | ER: %.0f -> %.0f | VA: %.2f }",
                damage, aliado.getEnergia(), energiaFinal, 1000/aliado.getVelActual());
        System.out.println();
        System.out.printf("PH: %.0f", ph);
        System.out.println();
    }

    public void incremento(Aliado aliado, double ph) {
        ph -= 2;
        if (ph < 0) {ph = 0;}
        double energiaActual = aliado.getEnergia();
        double energia = 75.0;
        double energiaFinal = energiaActual + energia;
        if (energiaFinal > aliado.getEnergiaMax()) {energiaFinal = aliado.getEnergiaMax();}

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf(aliado.getNombre() + " { Energía: %.0f -> %.0f | Ataque: %.0f -> %.0f | Velocidad: %.2f -> %.2f | VA: %.2f }",
                aliado.getEnergia(), energiaFinal,
                aliado.getAtqActual(), aliado.getAtqActual()+aliado.getAtqBase()*0.4,
                aliado.getVelActual(), aliado.getVelActual()+aliado.getVelActual()*0.3,
                (1000/(aliado.getVelActual()+aliado.getVelBase()*0.3))*0.5);
        System.out.println();
        System.out.printf("PH %.0f", ph);
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
        System.out.printf(aliado.getNombre() + " { Energía: %.0f -> %.0f | Puntos de Salud: %.0f -> %.0f | VA: %.2f }",
                aliado.getEnergia(), energiaFinal,
                aliado.getPsActual(), vidaCurada,
                1000/aliado.getVelActual());
        System.out.println();
        System.out.printf("PH %.0f", ph);
        System.out.println();
    }

    public void especial(Aliado aliado, Enemigo enemigo, double ph) {
        ph -= 1;
        if (ph < 0) {ph = 0;}
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

        double vulnerable = enemigo.getVulnerable().getVulnerable();
        if (vulnerable == 1 && eqReducido == 0) {
            vulnerable = 1.3;
        }
        double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }

        double vel = enemigo.getVelActual() - (enemigo.getVelBase()*0.2);
        if (vel < enemigo.getVelBase()/2) {
            vel = enemigo.getVelBase()/2;
        }

        va = va + ((1000/vel)-(1000/enemigo.getVelActual()));
        double multiplicador = 2;
        double damage = aliado.getAtqActual()*multiplicador*vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { EQ: %.0f -> %.0f | PS: %.0f -> %.0f | Vel: %.2f -> %.2f | VA: %.2f }",
                enemigo.getEquilibrio(), eqReducido, enemigo.getPsActual(), enemigo.getPsActual()- damage,
                enemigo.getVelActual(), vel, va);
        System.out.println();
        System.out.printf(aliado.getNombre() + " { Daño: %.0f | ER: %.0f -> %.0f | VA: %.2f }",
                damage, aliado.getEnergia(), energiaFinal,
                1000/(aliado.getVelActual()));
        System.out.println();
        System.out.printf("PH %.0f", ph);
        System.out.println();
    }

    public void definitiva(Aliado aliado, Enemigo enemigo, double ph, double phmax) {
        ph += 3;
        if (ph > phmax) {ph = phmax;}

        double eq = enemigo.getEquilibrio();
        double reducirEq = 40;
        double eqReducido = eq - reducirEq;
        if (eqReducido < 0) {
            eqReducido = 0;
        }
        double vulnerable = enemigo.getVulnerable().getVulnerable();
        if (vulnerable == 1 && eqReducido == 0) {
            vulnerable = 1.3;
        }
        double va = enemigo.getVaActual();
        if (vulnerable == 1.3) {
            va = va + (1000/enemigo.getVelActual())*(enemigo.atrasoEquilibrioRoto()/100);
        }
        double multiplicador = 4;
        double damage = aliado.getAtqActual() * multiplicador * vulnerable;

        System.out.println();
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { Equilibrio: %.2f -> %.2f | PS: %.2f -> %.2f | VA: %.2f }",
                enemigo.getEquilibrio(), eqReducido,
                enemigo.getPsActual(), enemigo.getPsActual() - damage,
                va);
        System.out.println();
        System.out.printf(aliado.getNombre() + ": { Daño infligido: %.2f | ER: 0 | VA: %.2f }",
                damage,
                1000/aliado.getVelActual()*(1-0.25));
        System.out.println();
        System.out.printf("PH %.0f", ph);
        System.out.println();
    }

    // -------------------- HABILIDADES ENEMIGO --------------------
    public void enemigoBasico(Aliado aliado, Enemigo enemigo) {
        double multiplicador = 1.2;
        double damage = enemigo.getAtqActual()*multiplicador;

        System.out.println(enemigo.getNombre().toUpperCase() + " USÓ EL ATAQUE BÁSICO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { Daño infligido: %.2f | VA: %.2f }",
                damage, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf(aliado.getNombre() + " { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - damage);
        System.out.println();
        System.out.println();
    }

    public void enemigoQuitar(Aliado aliado, Enemigo enemigo, double ph) {
        if (ph==0) {ph = 1;}

        double multiplicador = 1.4;
        double damage = enemigo.getAtqActual()*multiplicador;
        System.out.println(enemigo.getNombre().toUpperCase() + " USÓ EL ATAQUE QUITAR PH");
        System.out.println("Efectos de la habilidad: ");
        System.out.printf(enemigo.getNombre() + " { Daño infligido: %.2f | VA: %.2f }",
                damage, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf(aliado.getNombre() + " { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - damage);
        System.out.println();
        System.out.printf("PH: %.0f", ph - 1);
        System.out.println();
        System.out.println();
    }

    public void enemigoAutoBufo(Aliado aliado, Enemigo enemigo) {
        Double ataque = enemigo.getAtqActual()+enemigo.getAtqBase()*0.15;
        Double multiplicador = 1.5;
        double damage = ataque*multiplicador;

        System.out.println(enemigo.getNombre().toUpperCase() + " USÓ EL ATAQUE BUFO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { Ataque: %.2f -> %.2f | Daño infligido: %.2f | Velocidad: %.2f -> %.2f | VA: %.2f }",
                enemigo.getAtqActual(), ataque, damage,
                enemigo.getVelActual(), enemigo.getVelActual()+enemigo.getVelBase()*0.15, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf(aliado.getNombre() + " { PS: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - damage);
        System.out.println();
        System.out.println();
    }

    public void enemigoDebufo(Aliado aliado, Enemigo enemigo) {
        double energiaReducida = aliado.getEnergia()-10;
        if (energiaReducida < 0) {
            energiaReducida = 0.0;
        }
        double multiplicador = 1.5;
        double damage = enemigo.getAtqActual()*multiplicador;

        System.out.println(enemigo.getNombre().toUpperCase() + " USÓ EL ATAQUE DEBUFO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { Daño infligido: %.2f | VA: %.2f }",
                damage, 1000/enemigo.getVelActual());
        System.out.println();
        System.out.printf(aliado.getNombre() + " { PS: %.2f -> %.2f | Ataque: %.2f -> %.2f | Velocidad: %.2f -> %.2f | Energía: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - damage,
                aliado.getAtqActual(), aliado.getAtqActual()-aliado.getAtqBase()*0.1,
                aliado.getVelActual(), aliado.getVelActual()-aliado.getVelBase()*0.1,
                aliado.getEnergia(), energiaReducida);
        System.out.println();
        System.out.println();
    }

    public void enemigoDefinitiva(Aliado aliado, Enemigo enemigo) {
        double vidaCurada = enemigo.getPsActual() + enemigo.getPsMax()*0.15;
        if (vidaCurada > enemigo.getPsMax()) {
            vidaCurada = enemigo.getPsMax();
        }

        double adelanto = 40.0;
        double atraso = 20.0;
        double multiplicador = 1.5;
        double damage = enemigo.getAtqActual()*multiplicador;

        System.out.println(enemigo.getNombre().toUpperCase() + " USÓ EL ATAQUE DEFINITIVO");
        System.out.println("Efectos de la habilidad:");
        System.out.printf(enemigo.getNombre() + " { Daño infligido: %.2f | PS: %.2f -> %.2f | VA: %.2f }",
                damage, enemigo.getPsActual(), vidaCurada, 1000/enemigo.getVelActual()*(1-adelanto/100));
        System.out.println();
        System.out.printf(aliado.getNombre() + " { PS: %.2f -> %.2f | VA: %.2f -> %.2f }",
                aliado.getPsActual(), aliado.getPsActual() - damage,
                aliado.getVaActual(), aliado.getVaActual()+(1000/aliado.getVelActual())*(atraso/100));
        System.out.println();
        System.out.println();
    }
}

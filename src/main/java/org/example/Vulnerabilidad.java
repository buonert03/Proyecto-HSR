package org.example;

public enum Vulnerabilidad {
    NO(1.0),
    SI(1.3);

    private double vulnerable;

    Vulnerabilidad(double vulnerable) {
        this.vulnerable = vulnerable;
    }
    public double getVulnerable() {
        return vulnerable;
    }
}

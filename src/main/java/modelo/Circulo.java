package modelo;

import java.util.Objects;

public class Circulo extends Figura {

    private double radio;

    public Circulo(double radio) {
        super();
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public String getTipo() {
        return "CIRCULO";
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Circulo)) return false;
        Circulo otro = (Circulo) obj;
        return Double.compare(otro.radio, radio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radio);
    }

    @Override
    public String toString() {
        return "Circulo{radio=" + radio + '}';
    }
}

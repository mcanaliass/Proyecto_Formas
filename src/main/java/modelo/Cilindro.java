package modelo;

import java.util.Objects;

/**
 * Un cilindro se compone, por asociación, de una figura Circulo que
 * representa su base, más su propia altura.
 */
public class Cilindro extends Cuerpo {

    private Circulo base;
    private double altura;

    public Cilindro(Circulo base, double altura) {
        super();
        this.base = base;
        this.altura = altura;
    }

    public Cilindro(double radio, double altura) {
        this(new Circulo(radio), altura);
    }

    public Circulo getBase() {
        return base;
    }

    public void setBase(Circulo base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String getTipo() {
        return "CILINDRO";
    }

    @Override
    public double calcularPerimetro() {
        // Perímetro combinado de las dos bases circulares
        return 2 * base.calcularPerimetro();
    }

    @Override
    public double calcularArea() {
        // Área total = 2 bases + área lateral (perímetro de la base * altura)
        return 2 * base.calcularArea() + base.calcularPerimetro() * altura;
    }

    @Override
    public double calcularVolumen() {
        return base.calcularArea() * altura;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cilindro)) return false;
        Cilindro otro = (Cilindro) obj;
        return Objects.equals(base, otro.base)
                && Double.compare(otro.altura, altura) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, altura);
    }

    @Override
    public String toString() {
        return "Cilindro{radio=" + base.getRadio() + ", altura=" + altura + '}';
    }
}

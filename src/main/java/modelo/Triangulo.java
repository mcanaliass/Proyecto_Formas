package modelo;

import java.util.Objects;

/**
 * Clase abstracta que representa un triángulo genérico. La creación de
 * instancias se hace a través del método de fábrica crearTriangulo(),
 * que determina el subtipo (Equilatero, Isosceles o Escaleno) según
 * las medidas de los lados recibidos.
 */
public abstract class Triangulo extends Figura {

    private double lado1;
    private double lado2;
    private double lado3;

    protected Triangulo(double lado1, double lado2, double lado3) {
        super();
        validarDesigualdadTriangular(lado1, lado2, lado3);
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    private static void validarDesigualdadTriangular(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                    "Las medidas dadas no forman un triángulo válido.");
        }
    }

    /**
     * Método de fábrica: decide, según las medidas de los lados, si debe
     * instanciarse un Equilatero, un Isosceles o un Escaleno.
     */
    public static Triangulo crearTriangulo(double lado1, double lado2, double lado3) {
        boolean l1_igual_l2 = Double.compare(lado1, lado2) == 0;
        boolean l2_igual_l3 = Double.compare(lado2, lado3) == 0;
        boolean l1_igual_l3 = Double.compare(lado1, lado3) == 0;

        if (l1_igual_l2 && l2_igual_l3) {
            return new Equilatero(lado1);
        } else if (l1_igual_l2 || l2_igual_l3 || l1_igual_l3) {
            return new Isosceles(lado1, lado2, lado3);
        } else {
            return new Escaleno(lado1, lado2, lado3);
        }
    }

    public double getLado1() {
        return lado1;
    }

    public void setLado1(double lado1) {
        this.lado1 = lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public void setLado2(double lado2) {
        this.lado2 = lado2;
    }

    public double getLado3() {
        return lado3;
    }

    public void setLado3(double lado3) {
        this.lado3 = lado3;
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }

    /**
     * Área calculada mediante la fórmula de Herón, válida para cualquier
     * triángulo sin importar su subtipo específico.
     */
    @Override
    public double calcularArea() {
        double semi_perimetro = calcularPerimetro() / 2.0;
        return Math.sqrt(semi_perimetro
                * (semi_perimetro - lado1)
                * (semi_perimetro - lado2)
                * (semi_perimetro - lado3));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Triangulo)) return false;
        Triangulo otro = (Triangulo) obj;
        return Double.compare(otro.lado1, lado1) == 0
                && Double.compare(otro.lado2, lado2) == 0
                && Double.compare(otro.lado3, lado3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lado1, lado2, lado3);
    }

    @Override
    public String toString() {
        return getTipo() + "{lado1=" + lado1 + ", lado2=" + lado2 + ", lado3=" + lado3 + '}';
    }
}

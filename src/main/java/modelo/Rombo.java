package modelo;

import java.util.Objects;

public class Rombo extends Figura {

    private double lado;
    private double diagonal_mayor;
    private double diagonal_menor;

    public Rombo(double lado, double diagonal_mayor, double diagonal_menor) {
        super();
        this.lado = lado;
        this.diagonal_mayor = diagonal_mayor;
        this.diagonal_menor = diagonal_menor;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getDiagonalMayor() {
        return diagonal_mayor;
    }

    public void setDiagonalMayor(double diagonal_mayor) {
        this.diagonal_mayor = diagonal_mayor;
    }

    public double getDiagonalMenor() {
        return diagonal_menor;
    }

    public void setDiagonalMenor(double diagonal_menor) {
        this.diagonal_menor = diagonal_menor;
    }

    @Override
    public String getTipo() {
        return "ROMBO";
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public double calcularArea() {
        return (diagonal_mayor * diagonal_menor) / 2.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rombo)) return false;
        Rombo otro = (Rombo) obj;
        return Double.compare(otro.lado, lado) == 0
                && Double.compare(otro.diagonal_mayor, diagonal_mayor) == 0
                && Double.compare(otro.diagonal_menor, diagonal_menor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lado, diagonal_mayor, diagonal_menor);
    }

    @Override
    public String toString() {
        return "Rombo{lado=" + lado + ", diagonal_mayor=" + diagonal_mayor
                + ", diagonal_menor=" + diagonal_menor + '}';
    }
}

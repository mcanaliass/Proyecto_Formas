package modelo;

import java.util.Objects;

/**
 * Un tetraedro regular se compone, por asociación, de una cara triangular
 * equilátera. Sus 4 caras son congruentes y tiene 6 aristas.
 */
public class Tetraedro extends Cuerpo {

    private Equilatero cara;

    public Tetraedro(Equilatero cara) {
        super();
        this.cara = cara;
    }

    public Tetraedro(double arista) {
        this(new Equilatero(arista));
    }

    public Equilatero getCara() {
        return cara;
    }

    public void setCara(Equilatero cara) {
        this.cara = cara;
    }

    public double getArista() {
        return cara.getLado1();
    }

    @Override
    public String getTipo() {
        return "TETRAEDRO";
    }

    @Override
    public double calcularPerimetro() {
        // Suma de las 6 aristas del tetraedro
        return 6 * cara.getLado1();
    }

    @Override
    public double calcularArea() {
        // Área total de las 4 caras triangulares
        return 4 * cara.calcularArea();
    }

    @Override
    public double calcularVolumen() {
        double arista = cara.getLado1();
        return (Math.pow(arista, 3)) / (6 * Math.sqrt(2));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tetraedro)) return false;
        Tetraedro otro = (Tetraedro) obj;
        return Objects.equals(cara, otro.cara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cara);
    }

    @Override
    public String toString() {
        return "Tetraedro{arista=" + cara.getLado1() + '}';
    }
}

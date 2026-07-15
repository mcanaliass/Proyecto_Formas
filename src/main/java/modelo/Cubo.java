package modelo;

import java.util.Objects;

/**
 * Un cubo se compone, por asociación, de una cara cuadrada. A partir de
 * esa cara se derivan de forma indirecta su área total, su perímetro
 * (suma de aristas) y su volumen.
 */
public class Cubo extends Cuerpo {

    private Cuadrado cara;

    public Cubo(Cuadrado cara) {
        super();
        this.cara = cara;
    }

    public Cubo(double arista) {
        this(new Cuadrado(arista));
    }

    public Cuadrado getCara() {
        return cara;
    }

    public void setCara(Cuadrado cara) {
        this.cara = cara;
    }

    public double getArista() {
        return cara.getLado();
    }

    @Override
    public String getTipo() {
        return "CUBO";
    }

    @Override
    public double calcularPerimetro() {
        // Suma de las 12 aristas del cubo
        return 12 * cara.getLado();
    }

    @Override
    public double calcularArea() {
        // Área total de las 6 caras cuadradas
        return 6 * cara.calcularArea();
    }

    @Override
    public double calcularVolumen() {
        double arista = cara.getLado();
        return arista * arista * arista;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cubo)) return false;
        Cubo otro = (Cubo) obj;
        return Objects.equals(cara, otro.cara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cara);
    }

    @Override
    public String toString() {
        return "Cubo{arista=" + cara.getLado() + '}';
    }
}

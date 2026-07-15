package modelo;

import java.util.Objects;

/**
 * La esfera es la excepción de la jerarquía de Cuerpo: no guarda una
 * figura plana como atributo de asociación. En su lugar, sus cálculos
 * se derivan de forma indirecta a partir de un círculo máximo (su
 * "ecuador"), que se construye internamente solo para el cálculo y
 * no se conserva como atributo del objeto.
 */
public class Esfera extends Cuerpo {

    private double radio;

    public Esfera(double radio) {
        super();
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * Círculo máximo (ecuador) usado únicamente para derivar de forma
     * indirecta el área y el perímetro de la esfera. No es un atributo
     * persistente de la clase.
     */
    private Circulo circuloEcuador() {
        return new Circulo(radio);
    }

    @Override
    public String getTipo() {
        return "ESFERA";
    }

    @Override
    public double calcularPerimetro() {
        // Circunferencia del círculo máximo (ecuador)
        return circuloEcuador().calcularPerimetro();
    }

    @Override
    public double calcularArea() {
        // Área total = 4 veces el área del círculo máximo
        return 4 * circuloEcuador().calcularArea();
    }

    @Override
    public double calcularVolumen() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Esfera)) return false;
        Esfera otra = (Esfera) obj;
        return Double.compare(otra.radio, radio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radio);
    }

    @Override
    public String toString() {
        return "Esfera{radio=" + radio + '}';
    }
}

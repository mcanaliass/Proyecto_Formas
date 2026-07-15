package modelo;

public class Isosceles extends Triangulo {

    public Isosceles(double lado1, double lado2, double lado3) {
        super(lado1, lado2, lado3);
    }

    @Override
    public String getTipo() {
        return "TRIANGULO_ISOSCELES";
    }
}

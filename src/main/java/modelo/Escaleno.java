package modelo;

public class Escaleno extends Triangulo {

    public Escaleno(double lado1, double lado2, double lado3) {
        super(lado1, lado2, lado3);
    }

    @Override
    public String getTipo() {
        return "TRIANGULO_ESCALENO";
    }
}

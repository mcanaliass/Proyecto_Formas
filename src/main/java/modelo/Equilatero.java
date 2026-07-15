package modelo;

public class Equilatero extends Triangulo {

    public Equilatero(double lado) {
        super(lado, lado, lado);
    }

    @Override
    public String getTipo() {
        return "TRIANGULO_EQUILATERO";
    }
}

package modelo;

public class Cuadrado extends Rectangulo {

    public Cuadrado(double lado) {
        super(lado, lado);
    }

    public double getLado() {
        return getBase();
    }

    public void setLado(double lado) {
        setBase(lado);
        setAltura(lado);
    }

    @Override
    public String getTipo() {
        return "CUADRADO";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cuadrado)) return false;
        Cuadrado otro = (Cuadrado) obj;
        return Double.compare(otro.getLado(), getLado()) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getLado());
    }

    @Override
    public String toString() {
        return "Cuadrado{lado=" + getLado() + '}';
    }
}

package modelo;

/**
 * Clase abstracta raíz de la jerarquía de cuerpos sólidos.
 * Además de perímetro y área (heredados del contrato Forma), todo
 * cuerpo debe ser capaz de calcular su volumen.
 */
public abstract class Cuerpo implements Forma {

    private int id;

    protected Cuerpo() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getTipo();

    public abstract double calcularVolumen();

    @Override
    public void imprimirInformacion() {
        System.out.println("---- " + getTipo() + " ----");
        System.out.println(toString());
        System.out.printf("Perímetro: %.2f%n", calcularPerimetro());
        System.out.printf("Área: %.2f%n", calcularArea());
        System.out.printf("Volumen: %.2f%n", calcularVolumen());
    }
}

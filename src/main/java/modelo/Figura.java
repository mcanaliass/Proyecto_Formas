package modelo;

/**
 * Clase abstracta raíz de la jerarquía de figuras planas.
 * Toda figura concreta debe indicar su propio tipo (para persistencia
 * y para el menú) y calcular su perímetro y área según sus atributos.
 */
public abstract class Figura implements Forma {

    private int id;

    protected Figura() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Identificador de tipo usado para persistencia (columna "tipo" en BD)
     * y para reconstruir la figura correcta al leerla desde la base de datos.
     */
    public abstract String getTipo();

    @Override
    public void imprimirInformacion() {
        System.out.println("---- " + getTipo() + " ----");
        System.out.println(toString());
        System.out.printf("Perímetro: %.2f%n", calcularPerimetro());
        System.out.printf("Área: %.2f%n", calcularArea());
    }
}

package modelo;

/**
 * Interfaz que impone a toda figura o cuerpo del sistema la obligación
 * de calcular su perímetro, su área y de imprimir su información.
 */
public interface Forma {

    double calcularPerimetro();

    double calcularArea();

    void imprimirInformacion();
}

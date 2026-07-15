package presentacion;

import controlador.ColeccionControlador;

import java.util.Scanner;

/**
 * Cada opción del menú implementa esta interfaz. El Menu guarda una lista
 * de OpcionMenu y llama a ejecutar() sobre cada una; la implementación
 * real que corre depende del tipo concreto en tiempo de ejecución
 * (ligado dinámico), en vez de un switch gigante.
 */
public interface OpcionMenu {

    String getDescripcion();

    void ejecutar(ColeccionControlador controlador, Scanner entrada);
}

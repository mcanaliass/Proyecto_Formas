package presentacion;

import controlador.ColeccionControlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase auxiliar de la vista: contiene el menú de consola. Las opciones
 * se guardan como una lista de OpcionMenu; al invocar opcion.ejecutar(...)
 * el método real que corre depende del tipo concreto de cada opción
 * (ligado dinámico), en lugar de un switch gigante en esta clase.
 */
public class Menu {

    private final List<OpcionMenu> opciones;
    private final ColeccionControlador controlador;
    private final Scanner entrada;

    public Menu(ColeccionControlador controlador) {
        this.controlador = controlador;
        this.entrada = new Scanner(System.in);
        this.opciones = new ArrayList<>();
        opciones.add(new OpcionAgregarFigura());
        opciones.add(new OpcionAgregarCuerpo());
        opciones.add(new OpcionListarFiguras());
        opciones.add(new OpcionListarCuerpos());
        opciones.add(new OpcionAreaTotal());
        opciones.add(new OpcionPerimetroTotal());
        opciones.add(new OpcionVolumenTotal());
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            mostrarOpciones();
            int seleccion = leerOpcion();

            if (seleccion == 0) {
                continuar = false;
                System.out.println("¡Hasta luego!");
            } else if (seleccion >= 1 && seleccion <= opciones.size()) {
                // Ligado dinámico: se ejecuta la implementación concreta
                // correspondiente a la opción elegida.
                opciones.get(seleccion - 1).ejecutar(controlador, entrada);
            } else {
                System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
        entrada.close();
    }

    private void mostrarOpciones() {
        System.out.println("\n===== Proyecto Formas =====");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i).getDescripcion());
        }
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private int leerOpcion() {
        while (!entrada.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            entrada.next();
        }
        int valor = entrada.nextInt();
        entrada.nextLine();
        return valor;
    }
}

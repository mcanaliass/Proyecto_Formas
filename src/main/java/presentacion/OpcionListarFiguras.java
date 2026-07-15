package presentacion;

import controlador.ColeccionControlador;
import modelo.Figura;

import java.util.List;
import java.util.Scanner;

public class OpcionListarFiguras implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Listar todas las figuras";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        List<Figura> figuras = controlador.listarFiguras();
        if (figuras.isEmpty()) {
            System.out.println("\nNo hay figuras registradas todavía.");
            return;
        }
        System.out.println("\n=== Figuras registradas (" + figuras.size() + ") ===");
        for (Figura figura : figuras) {
            figura.imprimirInformacion();
            System.out.println();
        }
    }
}

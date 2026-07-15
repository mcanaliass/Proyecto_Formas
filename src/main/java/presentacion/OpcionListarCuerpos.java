package presentacion;

import controlador.ColeccionControlador;
import modelo.Cuerpo;

import java.util.List;
import java.util.Scanner;

public class OpcionListarCuerpos implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Listar todos los cuerpos";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        List<Cuerpo> cuerpos = controlador.listarCuerpos();
        if (cuerpos.isEmpty()) {
            System.out.println("\nNo hay cuerpos registrados todavía.");
            return;
        }
        System.out.println("\n=== Cuerpos registrados (" + cuerpos.size() + ") ===");
        for (Cuerpo cuerpo : cuerpos) {
            cuerpo.imprimirInformacion();
            System.out.println();
        }
    }
}

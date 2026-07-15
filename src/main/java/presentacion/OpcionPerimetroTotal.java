package presentacion;

import controlador.ColeccionControlador;

import java.util.Scanner;

public class OpcionPerimetroTotal implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Calcular perímetro total de la colección";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        System.out.printf("%nPerímetro total: %.2f%n", controlador.calcularPerimetroTotal());
    }
}

package presentacion;

import controlador.ColeccionControlador;

import java.util.Scanner;

public class OpcionVolumenTotal implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Calcular volumen total de la colección";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        System.out.printf("%nVolumen total: %.2f%n", controlador.calcularVolumenTotal());
    }
}

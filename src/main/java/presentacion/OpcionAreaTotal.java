package presentacion;

import controlador.ColeccionControlador;

import java.util.Scanner;

public class OpcionAreaTotal implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Calcular área total de la colección";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        System.out.printf("%nÁrea total: %.2f%n", controlador.calcularAreaTotal());
    }
}

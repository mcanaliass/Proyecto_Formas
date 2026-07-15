package presentacion;

import controlador.ColeccionControlador;
import modelo.*;

import java.util.Scanner;

public class OpcionAgregarCuerpo implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Agregar cuerpo nuevo";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        System.out.println("\nTipos de cuerpo disponibles:");
        System.out.println("1. Cubo");
        System.out.println("2. Cilindro");
        System.out.println("3. Tetraedro");
        System.out.println("4. Esfera");
        System.out.print("Elige un tipo: ");
        int tipo = leerEntero(entrada);

        Cuerpo cuerpo = null;
        switch (tipo) {
            case 1: {
                double arista = pedirDouble(entrada, "Arista: ");
                cuerpo = new Cubo(arista);
                break;
            }
            case 2: {
                double radio = pedirDouble(entrada, "Radio de la base: ");
                double altura = pedirDouble(entrada, "Altura: ");
                cuerpo = new Cilindro(radio, altura);
                break;
            }
            case 3: {
                double arista = pedirDouble(entrada, "Arista: ");
                cuerpo = new Tetraedro(arista);
                break;
            }
            case 4: {
                double radio = pedirDouble(entrada, "Radio: ");
                cuerpo = new Esfera(radio);
                break;
            }
            default:
                System.out.println("Opción no válida.");
                return;
        }

        controlador.agregarCuerpo(cuerpo);
        System.out.println("Cuerpo agregado correctamente:");
        cuerpo.imprimirInformacion();
    }

    private int leerEntero(Scanner entrada) {
        while (!entrada.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            entrada.next();
        }
        int valor = entrada.nextInt();
        entrada.nextLine();
        return valor;
    }

    private double pedirDouble(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        while (!entrada.hasNextDouble()) {
            System.out.print("Ingresa un número válido: ");
            entrada.next();
        }
        double valor = entrada.nextDouble();
        entrada.nextLine();
        return valor;
    }
}

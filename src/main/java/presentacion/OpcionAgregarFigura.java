package presentacion;

import controlador.ColeccionControlador;
import modelo.*;

import java.util.Scanner;

public class OpcionAgregarFigura implements OpcionMenu {

    @Override
    public String getDescripcion() {
        return "Agregar figura nueva";
    }

    @Override
    public void ejecutar(ColeccionControlador controlador, Scanner entrada) {
        System.out.println("\nTipos de figura disponibles:");
        System.out.println("1. Circulo");
        System.out.println("2. Rectangulo");
        System.out.println("3. Cuadrado");
        System.out.println("4. Rombo");
        System.out.println("5. Triangulo");
        System.out.print("Elige un tipo: ");
        int tipo = leerEntero(entrada);

        Figura figura = null;
        switch (tipo) {
            case 1: {
                double radio = pedirDouble(entrada, "Radio: ");
                figura = new Circulo(radio);
                break;
            }
            case 2: {
                double base = pedirDouble(entrada, "Base: ");
                double altura = pedirDouble(entrada, "Altura: ");
                figura = new Rectangulo(base, altura);
                break;
            }
            case 3: {
                double lado = pedirDouble(entrada, "Lado: ");
                figura = new Cuadrado(lado);
                break;
            }
            case 4: {
                double lado = pedirDouble(entrada, "Lado: ");
                double diagonalMayor = pedirDouble(entrada, "Diagonal mayor: ");
                double diagonalMenor = pedirDouble(entrada, "Diagonal menor: ");
                figura = new Rombo(lado, diagonalMayor, diagonalMenor);
                break;
            }
            case 5: {
                double lado1 = pedirDouble(entrada, "Lado 1: ");
                double lado2 = pedirDouble(entrada, "Lado 2: ");
                double lado3 = pedirDouble(entrada, "Lado 3: ");
                try {
                    figura = Triangulo.crearTriangulo(lado1, lado2, lado3);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
                break;
            }
            default:
                System.out.println("Opción no válida.");
                return;
        }

        controlador.agregarFigura(figura);
        System.out.println("Figura agregada correctamente:");
        figura.imprimirInformacion();
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

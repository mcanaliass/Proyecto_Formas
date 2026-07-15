package presentacion;

import controlador.ColeccionControlador;
import datos.ConexionBD;

public class Main {

    public static void main(String[] args) {
        ColeccionControlador controlador = new ColeccionControlador();

        // Descomenta la siguiente línea si quieres cargar al iniciar
        // las figuras/cuerpos ya guardados en la base de datos:
        // controlador.cargarDesdeBaseDeDatos();

        Menu menu = new Menu(controlador);
        menu.iniciar();

        ConexionBD.cerrarConexion();
    }
}

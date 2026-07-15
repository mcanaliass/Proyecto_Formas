package presentacion;

import controlador.ColeccionControlador;
import datos.ConexionBD;

public class Main {

    public static void main(String[] args) {
        ColeccionControlador controlador = new ColeccionControlador();

        // Opcion de cargar info de sesiones anteriores:
        // controlador.cargarDesdeBaseDeDatos();

        Menu menu = new Menu(controlador);
        menu.iniciar();

        ConexionBD.cerrarConexion();
    }
}

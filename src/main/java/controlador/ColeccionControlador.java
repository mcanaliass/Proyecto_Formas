package controlador;

import datos.CuerpoDAO;
import datos.CuerpoDAOImpl;
import datos.FiguraDAO;
import datos.FiguraDAOImpl;
import modelo.Coleccion;
import modelo.Cuerpo;
import modelo.Figura;

import java.util.List;

/**
 * Controlador (patrón MVC): media entre la vista (Menu) y el modelo
 * (Coleccion), delegando la persistencia a la capa DAO.
 */
public class ColeccionControlador {

    private final Coleccion coleccion;
    private final FiguraDAO figuraDAO;
    private final CuerpoDAO cuerpoDAO;

    public ColeccionControlador() {
        this.coleccion = new Coleccion();
        this.figuraDAO = new FiguraDAOImpl();
        this.cuerpoDAO = new CuerpoDAOImpl();
    }

    public void agregarFigura(Figura figura) {
        figuraDAO.insertar(figura);
        coleccion.agregarFigura(figura);
    }

    public void agregarCuerpo(Cuerpo cuerpo) {
        cuerpoDAO.insertar(cuerpo);
        coleccion.agregarCuerpo(cuerpo);
    }

    public List<Figura> listarFiguras() {
        return coleccion.listarFiguras();
    }

    public List<Cuerpo> listarCuerpos() {
        return coleccion.listarCuerpos();
    }

    public double calcularAreaTotal() {
        return coleccion.calcularAreaTotal();
    }

    public double calcularPerimetroTotal() {
        return coleccion.calcularPerimetroTotal();
    }

    public double calcularVolumenTotal() {
        return coleccion.calcularVolumenTotal();
    }

    /** Carga en memoria las figuras y cuerpos previamente guardados en la BD. */
    public void cargarDesdeBaseDeDatos() {
        for (Figura figura : figuraDAO.listarTodos()) {
            coleccion.agregarFigura(figura);
        }
        for (Cuerpo cuerpo : cuerpoDAO.listarTodos()) {
            coleccion.agregarCuerpo(cuerpo);
        }
    }
}

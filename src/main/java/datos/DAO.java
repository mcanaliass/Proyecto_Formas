package datos;

import java.util.List;

/**
 * Interfaz genérica del patrón DAO (Data Access Object).
 */
public interface DAO<T> {

    void insertar(T objeto);

    List<T> listarTodos();

    boolean eliminar(int id);
}

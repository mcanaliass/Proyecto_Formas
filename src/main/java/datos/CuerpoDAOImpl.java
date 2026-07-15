package datos;

import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuerpoDAOImpl implements CuerpoDAO {

    private static final String INSERTAR_SQL =
            "INSERT INTO cuerpos (tipo, figura_id, altura, radio) VALUES (?, ?, ?, ?)";

    private static final String LISTAR_SQL = "SELECT * FROM cuerpos";

    private static final String ELIMINAR_SQL = "DELETE FROM cuerpos WHERE id = ?";

    private final FiguraDAOImpl figuraDAO = new FiguraDAOImpl();

    @Override
    public void insertar(Cuerpo cuerpo) {
        try {
            Connection con = ConexionBD.obtenerConexion();

            Integer figuraId = null;

            if (cuerpo instanceof Cubo) {
                Cuadrado cara = ((Cubo) cuerpo).getCara();
                figuraDAO.insertar(cara);
                figuraId = cara.getId();
            } else if (cuerpo instanceof Tetraedro) {
                Equilatero cara = ((Tetraedro) cuerpo).getCara();
                figuraDAO.insertar(cara);
                figuraId = cara.getId();
            } else if (cuerpo instanceof Cilindro) {
                Circulo base = ((Cilindro) cuerpo).getBase();
                figuraDAO.insertar(base);
                figuraId = base.getId();
            }
            // Esfera no tiene figura asociada: figuraId queda null

            PreparedStatement ps = con.prepareStatement(INSERTAR_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cuerpo.getTipo());

            if (figuraId != null) {
                ps.setInt(2, figuraId);
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (cuerpo instanceof Cilindro) {
                ps.setDouble(3, ((Cilindro) cuerpo).getAltura());
            } else {
                ps.setNull(3, java.sql.Types.DOUBLE);
            }

            if (cuerpo instanceof Esfera) {
                ps.setDouble(4, ((Esfera) cuerpo).getRadio());
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }

            ps.executeUpdate();

            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    cuerpo.setId(claves.getInt(1));
                }
            }
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error al insertar cuerpo: " + e.getMessage());
        }
    }

    @Override
    public List<Cuerpo> listarTodos() {
        List<Cuerpo> resultado = new ArrayList<>();
        try {
            Connection con = ConexionBD.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(LISTAR_SQL);

            while (rs.next()) {
                Cuerpo cuerpo = construirCuerpoDesdeFila(con, rs);
                if (cuerpo != null) {
                    resultado.add(cuerpo);
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Error al listar cuerpos: " + e.getMessage());
        }
        return resultado;
    }

    private Cuerpo construirCuerpoDesdeFila(Connection con, ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        Cuerpo cuerpo;

        switch (tipo) {
            case "CUBO": {
                Figura cara = obtenerFigura(con, rs.getInt("figura_id"));
                cuerpo = new Cubo((Cuadrado) cara);
                break;
            }
            case "TETRAEDRO": {
                Figura cara = obtenerFigura(con, rs.getInt("figura_id"));
                cuerpo = new Tetraedro((Equilatero) cara);
                break;
            }
            case "CILINDRO": {
                Figura base = obtenerFigura(con, rs.getInt("figura_id"));
                cuerpo = new Cilindro((Circulo) base, rs.getDouble("altura"));
                break;
            }
            case "ESFERA":
                cuerpo = new Esfera(rs.getDouble("radio"));
                break;
            default:
                return null;
        }
        cuerpo.setId(rs.getInt("id"));
        return cuerpo;
    }

    private Figura obtenerFigura(Connection con, int figuraId) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM figuras WHERE id = ?")) {
            ps.setInt(1, figuraId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return figuraDAO.construirFiguraDesdeFila(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        try {
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(ELIMINAR_SQL);
            ps.setInt(1, id);
            boolean resultado = ps.executeUpdate() > 0;
            ps.close();
            return resultado;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cuerpo: " + e.getMessage());
            return false;
        }
    }
}
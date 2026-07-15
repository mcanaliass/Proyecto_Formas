package datos;

import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FiguraDAOImpl implements FiguraDAO {

    private static final String INSERTAR_SQL =
            "INSERT INTO figuras (tipo, lado1, lado2, lado3, radio, base, altura, "
                    + "diagonal_mayor, diagonal_menor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String LISTAR_SQL = "SELECT * FROM figuras";

    private static final String ELIMINAR_SQL = "DELETE FROM figuras WHERE id = ?";

    @Override
    public void insertar(Figura figura) {
        try {
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(INSERTAR_SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, figura.getTipo());

            if (figura instanceof Triangulo) {
                Triangulo t = (Triangulo) figura;
                ps.setDouble(2, t.getLado1());
                ps.setDouble(3, t.getLado2());
                ps.setDouble(4, t.getLado3());
                ps.setNull(5, java.sql.Types.DOUBLE);
                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setNull(7, java.sql.Types.DOUBLE);
                ps.setNull(8, java.sql.Types.DOUBLE);
                ps.setNull(9, java.sql.Types.DOUBLE);
            } else if (figura instanceof Circulo) {
                Circulo c = (Circulo) figura;
                ps.setNull(2, java.sql.Types.DOUBLE);
                ps.setNull(3, java.sql.Types.DOUBLE);
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setDouble(5, c.getRadio());
                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setNull(7, java.sql.Types.DOUBLE);
                ps.setNull(8, java.sql.Types.DOUBLE);
                ps.setNull(9, java.sql.Types.DOUBLE);
            } else if (figura instanceof Rectangulo) {
                // Cubre también Cuadrado, que hereda de Rectangulo
                Rectangulo r = (Rectangulo) figura;
                ps.setNull(2, java.sql.Types.DOUBLE);
                ps.setNull(3, java.sql.Types.DOUBLE);
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setNull(5, java.sql.Types.DOUBLE);
                ps.setDouble(6, r.getBase());
                ps.setDouble(7, r.getAltura());
                ps.setNull(8, java.sql.Types.DOUBLE);
                ps.setNull(9, java.sql.Types.DOUBLE);
            } else if (figura instanceof Rombo) {
                Rombo r = (Rombo) figura;
                ps.setDouble(2, r.getLado());
                ps.setNull(3, java.sql.Types.DOUBLE);
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setNull(5, java.sql.Types.DOUBLE);
                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setNull(7, java.sql.Types.DOUBLE);
                ps.setDouble(8, r.getDiagonalMayor());
                ps.setDouble(9, r.getDiagonalMenor());
            }

            ps.executeUpdate();

            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    figura.setId(claves.getInt(1));
                }
            }
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error al insertar figura: " + e.getMessage());
        }
    }

    @Override
    public List<Figura> listarTodos() {
        List<Figura> resultado = new ArrayList<>();
        try {
            Connection con = ConexionBD.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(LISTAR_SQL);

            while (rs.next()) {
                Figura figura = construirFiguraDesdeFila(rs);
                if (figura != null) {
                    resultado.add(figura);
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Error al listar figuras: " + e.getMessage());
        }
        return resultado;
    }

    Figura construirFiguraDesdeFila(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        Figura figura;

        switch (tipo) {
            case "CIRCULO":
                figura = new Circulo(rs.getDouble("radio"));
                break;
            case "RECTANGULO":
                figura = new Rectangulo(rs.getDouble("base"), rs.getDouble("altura"));
                break;
            case "CUADRADO":
                figura = new Cuadrado(rs.getDouble("base"));
                break;
            case "ROMBO":
                figura = new Rombo(rs.getDouble("lado1"),
                        rs.getDouble("diagonal_mayor"), rs.getDouble("diagonal_menor"));
                break;
            case "TRIANGULO_EQUILATERO":
            case "TRIANGULO_ISOSCELES":
            case "TRIANGULO_ESCALENO":
                figura = Triangulo.crearTriangulo(rs.getDouble("lado1"),
                        rs.getDouble("lado2"), rs.getDouble("lado3"));
                break;
            default:
                return null;
        }
        figura.setId(rs.getInt("id"));
        return figura;
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
            System.err.println("Error al eliminar figura: " + e.getMessage());
            return false;
        }
    }
}
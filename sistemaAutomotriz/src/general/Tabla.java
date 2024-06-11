package general;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabla {
    private Connection connection;

    public Tabla(Connection connection) {
        this.connection = connection;
    }
    public void crearTablaPersona() {
        if (!tablaExiste("PERSONA")) {
            String sql = "CREATE TABLE persona ("
                       + "personaID NUMBER PRIMARY KEY,"
                       + "nombre VARCHAR2(50) NOT NULL,"
                       + "apellidoPaterno VARCHAR2(50) NOT NULL,"
                       + "apellidoMaterno VARCHAR2(50) NOT NULL,"
                       + "fechaNacimiento DATE NOT NULL,"
                       + "fechaFin DATE"
                       + ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
                System.out.println("Tabla persona creada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La tabla persona ya existe.");
        }
    }

    private boolean tablaExiste(String nombreTabla) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM user_tables WHERE table_name = '" + nombreTabla.toUpperCase() + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }
}

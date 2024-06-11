package principal;

/*  private static final String URL = "jdbc:oracle:thin:@//129.146.122.209:1521/pdbautomotriz";
    private static final String USER = "COMPRAS";
    private static final String PASSWORD = "ORACLE1";*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL = "jdbc:oracle:thin:@//129.146.122.209:1521/pdbautomotriz";
	private static final String USER = "COMPRAS";
	private static final String PASSWORD = "ORACLE1";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

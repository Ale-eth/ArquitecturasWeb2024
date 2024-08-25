package tudai.arqui.tp1.ej1;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
// Guardo el driver en un String
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

// Creo una instancia del driver de la base de datos que voy a usar (Derby)
        Class.forName(driver).getDeclaredConstructor().newInstance();

// Guardo la URI de la base de datos en un String
        String uri = "jdbc:derby:MyDerbyDB;create=true";

        try {
            Connection con = DriverManager.getConnection(uri);
            createTables(con);
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fin MAIN
    private static void createTables(Connection con) {
        String table = "CREATE TABLE persona(id INT PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50))";

        try {
            con.prepareStatement(table).execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPersona(Connection con, int id, String nombre, String apellido) throws SQLException {
        String insert = "INSERT INTO persona VALUES(?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(insert);

        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, apellido);

        ps.executeUpdate();
        ps.close();
        ps.execute();
    }
}


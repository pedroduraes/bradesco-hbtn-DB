import java.sql.DriverManager;
import java.sql.SQLException;

public class GestaoCursosMain {
    public static void main( String[] args )
    {
        System.out.println( "Iniciando aplicacao!" );
        connect();
    }

        public static void connect() {
        // connection string
        var url = "jdbc:sqlite:database_admin_jpa.db";

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

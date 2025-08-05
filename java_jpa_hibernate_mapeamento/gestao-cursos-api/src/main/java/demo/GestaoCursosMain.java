import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.AlunoModel;

public class GestaoCursosMain {
    public static void main( String[] args )
    {
        System.out.println( "Iniciando aplicacao!" );
        connect();
    }

        public static void connect() {
        // connection string
        //var url = "jdbc:sqlite:database_admin_jpa.db";
        var url = "jdbc:sqlite:database_admin_jpa.db";

        try (var conn = DriverManager.getConnection(url)) {
            AlunoModel alunoModel = new AlunoModel();
            System.out.println("Connection to SQLite has been established.");

            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // Espera so por 30 segundos para conectar

            // Roda os comandos para o SQLite
            statement.executeUpdate("DROP TABLE IF EXISTS terminalroot");
            statement.executeUpdate("CREATE TABLE terminalroot (id INTEGER, name STRING)");
            statement.executeUpdate("INSERT INTO terminalroot VALUES(1, 'Marcos Oliveira')");
            statement.executeUpdate("INSERT INTO terminalroot VALUES(2, 'James Gosling')");
            ResultSet rs = statement.executeQuery("SELECT * FROM terminalroot");
            while(rs.next()) {
                // Ler os dados inseridos
                System.out.println("NOME DO CARA  : " + rs.getString("name"));
                System.out.println("IDENTIFICACAO : " + rs.getInt("id"));
            }
            


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

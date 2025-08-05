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
        var url = "jdbc:sqlite:database_admin_jpa.db";

        try (var conn = DriverManager.getConnection(url)) {
            AlunoModel alunoModel = new AlunoModel();
            System.out.println("Connection to SQLite has been established.");

            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // Espera so por 30 segundos para conectar

            // Roda os comandos para o SQLite
            statement.executeUpdate("DROP TABLE IF EXISTS Alunos");
            statement.executeUpdate("CREATE TABLE Alunos (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeCompleto STRING, matricula STRING, nascimento DATE, email STRING)");
            statement.executeUpdate("INSERT INTO Alunos VALUES(null,'Marcos Oliveira', '1', date(), 'marcos@email.com')");
            statement.executeUpdate("INSERT INTO Alunos VALUES(null,'Maria Oliveira', '2', date(), 'maria@email.com')");
            
            ResultSet rs = statement.executeQuery("SELECT * FROM Alunos");
            while(rs.next()) {
                // Ler os dados inseridos
                System.out.println("IDENTIFICACAO   : " + rs.getInt("id"));
                System.out.println("NOME            : " + rs.getString("nomeCompleto"));
                System.out.println("Data Nasc.      : " + rs.getString("nascimento"));
                System.out.println("Email           : " + rs.getString("email"));
                
            }
            


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package demo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import entities.Aluno;
import entities.Curso;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {
	
	
	
    public static void main( String[] args )
    {
        System.out.println( "Iniciando aplicacao!" );
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        connect();
        listaAlunos();
        criarAluno();
        alterarAluno();
        deletarAluno();
        
        criarCurso();
        System.exit(0);
    }

        public static void connect() {
        // connection string
        var url = "jdbc:sqlite:database_admin_jpa.db";

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
            
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // Espera so por 30 segundos para conectar
            // Roda os comandos para o SQLite
            statement.executeUpdate("DROP TABLE IF EXISTS Alunos");
            statement.executeUpdate("DROP TABLE IF EXISTS Cursos");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public static void criarAluno() {
        	System.out.println("Criando alunos");
        	AlunoModel alunoModel = new AlunoModel();
        	Aluno aluno = new Aluno(null, "Pedro", "p0", new Date(), "pedro@bradesco.com.br") ;
            alunoModel.create(aluno);
            
            for(var i=0; i< 10; i++) {
            	aluno = new Aluno(null, "Aluno "+ i, "p"+i, new Date(), String.format("aluno%d@bradesco.com.br", i)) ;
                alunoModel.create(aluno);
            }
            listaAlunos();
        }
        
        
        
        public static void listaAlunos() {
        	System.out.println("Listando alunos.");
        	AlunoModel alunoModel = new AlunoModel();
        	List<Aluno> alunos = alunoModel.findAll();
        	if (alunos.isEmpty()) {
        		System.out.println("Nao existem alunos.");
        	}
            for (Aluno a : alunos) {
            	System.out.println("id: " + a.getId());
            	System.out.println("nome: " + a.getNomeCompleto());
            	System.out.println("nascimento: " + a.getNascimento());
            	System.out.println("email: " + a.getEmail());
			}
            System.out.println("----------------------------------------------------------------");
        }
        
        public static void alterarAluno() {
        	System.out.println("Alterando aluno.");
        	AlunoModel alunoModel = new AlunoModel();
        	Aluno aluno = alunoModel.findById(1L);
        	aluno.setNomeCompleto("Pedro Duraes");
        	alunoModel.update(aluno);
        	listaAlunos();
        	
        	
        }
        
        public static void deletarAluno() {
        	System.out.println("Excluindo aluno.");
        	AlunoModel alunoModel = new AlunoModel();
        	Aluno aluno = alunoModel.findById(1L);
        	alunoModel.delete(aluno);
        	listaAlunos();
        	
        }
        
        
        public static void criarCurso() {
        	CursoModel cursoModel = new CursoModel();
        	Curso curso;
        	curso = new Curso(null, "Holberto", "hbt");
        	cursoModel.create(curso);
        	
        	curso = new Curso(null, "Java 17", "jdk17");
        	cursoModel.create(curso);
        	
        	
        	for(int i =0; i< 10; i++) {
        		curso = new Curso(null, "Curso "+ i, "sigla"+i);
            	cursoModel.create(curso);
        	}
        	
        	listaCursos();
        }
        
        public static void listaCursos() {
        	System.out.println("Listando cursos.");
        	CursoModel cursoModel = new CursoModel();
        	List<Curso> cursos = cursoModel.findAll();
        	if (cursos.isEmpty()) {
        		System.out.println("Nao existem cursos.");
        	}
            for (Curso a : cursos) {
            	System.out.println("id: " + a.getId());
            	System.out.println("nome: " + a.getNome());
            	System.out.println("sigla: " + a.getSigla());
            	
			}
            System.out.println("----------------------------------------------------------------");
        }
}

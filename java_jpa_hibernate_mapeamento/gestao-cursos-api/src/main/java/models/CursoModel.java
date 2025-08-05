package models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Curso;

public class CursoModel {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
	
	public void create(Curso curso) {
		
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transaÃ§Ã£o");
			em.getTransaction().begin();
			em.persist(curso);
			em.getTransaction().commit();
			System.out.println("Curso criado com sucesso !!!" + curso.getId());
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar um curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transaÃ§Ã£o");
		}
	}

	public Curso findById(Long id) {
		EntityManager em = emf.createEntityManager();
        return em.find(Curso.class, id);
	}

	public List<Curso> findAll() {
		EntityManager em = emf.createEntityManager();
    	TypedQuery<Curso> query = em.createQuery("SELECT a FROM Curso a", Curso.class);
        // Execute the query and return the result list
        return query.getResultList();
	}

	public void update(Curso curso) {
		EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();
    		em.merge(curso);	
    		em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao atualizar curso!!!" + e.getMessage());
		}
    	finally {
    		em.close();
    	}
	}

	public void delete(Curso curso) {
		EntityManager em = emf.createEntityManager();
    	try {
    		Curso cursoToDelete = em.find(Curso.class, curso.getId());
    		em.getTransaction().begin();
    		em.remove(cursoToDelete);	
    		em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao excluir curso !!!" + e.getMessage());
		}
    	finally {
    		em.close();
    	}
	}

}

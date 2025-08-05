package models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Aluno;

public class AlunoModel {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
	
    public void create(Aluno aluno) {
    	
        EntityManager em = emf.createEntityManager();        

        try {
            System.out.println("Iniciando a transaÃ§Ã£o");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso id:" + em.find(Aluno.class, 1L).getId());
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transaÃ§Ã£o");
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Aluno.class, id);
    }

    public  List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();
    	TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
        // Execute the query and return the result list
        return query.getResultList();
    }

    public void update(Aluno aluno) {
    	EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();
    		em.merge(aluno);	
    		em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao atualizar aluno !!!" + e.getMessage());
		}
    	finally {
    		em.close();
    	}

    }

    public void delete(Aluno aluno) {
    	EntityManager em = emf.createEntityManager();
    	try {
    		Aluno alunoToDelete = em.find(Aluno.class, aluno.getId());
    		em.getTransaction().begin();
    		em.remove(alunoToDelete);	
    		em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao excluir aluno !!!" + e.getMessage());
		}
    	finally {
    		em.close();
    	}
    }
}
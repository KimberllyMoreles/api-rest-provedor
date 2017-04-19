package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Produtor;

public class ProdutorDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public ProdutorDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Produtor incluir(Produtor produtor) {
        try {
            em.getTransaction().begin();
            em.persist(produtor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return produtor;
    }

    public Produtor alterar(Produtor produtor) {
        try {
            em.getTransaction().begin();
            em.merge(produtor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return produtor;
    }

    public Boolean excluir(Produtor produtor) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(produtor);
            em.getTransaction().commit();
            retorno = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            retorno = false;
            throw e;
        }
        return retorno;
    }

    public Boolean excluir(int chaveprimaria) {
        Boolean retorno;
        try {
            Produtor obj = this.buscarPorChavePrimaria(chaveprimaria);
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            retorno = false;
            throw e;
        }
        return retorno;
    }

    public List<Produtor> listar() {
        return em.createNamedQuery("Produtor.findAll").getResultList();
    }

    public Produtor buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Produtor.class, chaveprimaria);
    }
}

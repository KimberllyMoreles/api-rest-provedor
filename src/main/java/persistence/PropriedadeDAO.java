package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Propriedade;

public class PropriedadeDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public PropriedadeDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Propriedade incluir(Propriedade propriedade, int idprodutor) {
        try {
            propriedade.getProdutor().setId(idprodutor);
            em.getTransaction().begin();
            em.persist(propriedade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return propriedade;
    }

    public Propriedade alterar(Propriedade propriedade) {
        try {
            em.getTransaction().begin();
            em.merge(propriedade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return propriedade;
    }

    public Boolean excluir(Propriedade propriedade) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(propriedade);
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
            Propriedade obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<Propriedade> listar() {
        return em.createNamedQuery("Propriedade.findAll").getResultList();
    }

    public Propriedade buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Propriedade.class, chaveprimaria);
    }
}

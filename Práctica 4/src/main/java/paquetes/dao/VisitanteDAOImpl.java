package paquetes.dao;

import javax.persistence.*;

import paquetes.model.Visitante;

public class VisitanteDAOImpl implements VisitanteDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUP");

    @Override
    public void guardar(Visitante visitante) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(visitante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
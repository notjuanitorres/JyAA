package paquetes.dao;

import paquetes.model.ItemCasamiento;

import javax.persistence.*;
import java.util.List;

public class ItemCasamientoDAOImpl implements ItemCasamientoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUP");

    @Override
    public List<ItemCasamiento> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM ItemCasamiento i", ItemCasamiento.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void guardar(ItemCasamiento item) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(item);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
package edu.chl.hajo.shop.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Base class for any persisting collection DAO = Data access object (also
 * called a Facade)
 *
 *   *** TODO Implement all methods ***
 *
 * @param <T> Type
 * @param <K> Primary key (id)
 * @author hajo
 */
public abstract class AbstractDAO<T, K> implements IDAO<T, K> {

    private final Class<T> clazz;

    // To be overridden by subclasses
    protected abstract EntityManager getEntityManager();

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T t) {
       getEntityManager().persist(t);
    }

    @Override
    public void delete(K id) {
        T t = getEntityManager().getReference(clazz,id);
        getEntityManager().remove(t);
    }

    /**
     * Using out parameter
     *
     * @param t
     */
    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz,id);
    }

    @Override
    public List<T> findAll() {
        return get(true, -1, -1);
    }

    @Override
    public List<T> findRange(int first, int n) {
        return get(false, first, n);
    }

    //@SuppressWarnings("")
    private List<T> get(boolean all, int first, int n) {
        EntityManager em = getEntityManager();
        List<T> found = new ArrayList<>();
        TypedQuery<T> q = em.createQuery("select t from " + clazz.getSimpleName() + " t", clazz);
        if (!all) {
            q.setFirstResult(first);
            q.setMaxResults(n);
        }
        found.addAll(q.getResultList());
        return found;
    }

    @Override
    public int count() {
        EntityManager em = getEntityManager();
        Long n = em.createQuery("select count(t) from " + clazz.getSimpleName() + " t", Long.class)
                .getSingleResult();
        return n.intValue();
    } 
    
    
    /*
    @Override
    public void create(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void delete(K id) {
        getEntityManager().remove(getEntityManager().merge(id));
    }

    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery(clazz);
        cq.select(cq.from(clazz));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<T> findRange(int first, int n) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery(clazz);
        cq.select(cq.from(clazz));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(n - first);
        q.setFirstResult(first);
        return q.getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery(clazz);
        Root<T> rt = cq.from(clazz);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    } */
}

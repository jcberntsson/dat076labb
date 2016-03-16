package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All customers 
 *
 * @author hajo
 */

@Stateless
public class CustomerRegistry extends AbstractDAO<Customer, Long> implements ICustomerRegistry {

   // TODO also some coding

    @PersistenceContext
    private EntityManager em;

    public CustomerRegistry() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Customer> getByName(String name) {
        List<Customer> list = this.findAll();
        List<Customer> results = new ArrayList();
        for(Customer p : list){
            if((p.getFname() + " " + p.getLname()).equals(name)){
                results.add(p);
            }
        }
        return results;
    }

}

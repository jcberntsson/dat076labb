package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All orders 
 *
 * @author hajo
 */
@Stateless
public class OrderBook extends AbstractDAO<PurchaseOrder, Long> implements IOrderBook {

    @PersistenceContext
    private EntityManager em;

    public OrderBook() {
        super(PurchaseOrder.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    // TODO also some coding

    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        List<PurchaseOrder> list = this.findAll();
        List<PurchaseOrder> results = new ArrayList();
        for(PurchaseOrder p : list){
            if(p.getCustomer().equals(c)){
                results.add(p);
            }
        }
        return results;
    }

}

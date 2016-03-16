package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All products (stateless EJB)
 *
 * @author hajo
 */
@Stateless
public class ProductCatalogue extends AbstractDAO<Product, Long> implements IProductCatalogue {
    
    @PersistenceContext
    private EntityManager em;
    
    public ProductCatalogue() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> list = this.findAll();
        List<Product> results = new ArrayList();
        for(Product p : list){
            if(p.getName().equals(name)){
                results.add(p);
            }
        }
        return results;
    }

}

package edu.chl.hajo.shop.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * Shop is a container for other containers NOTE: Uses Java 7
 *
 * NOTE: Can't init test data here 


        *** Nothing to do here ***
 * 
 * @author hajo
 */
@Named
@ApplicationScoped
public class Shop implements IShop {
    //@Inject will not work!!! Because of interfaces+EBJs or similar
    // If interfaces removed it will work
    @EJB
    private IProductCatalogue productCatalogue;
    @EJB
    private ICustomerRegistry customerRegistry;
    @EJB
    private IOrderBook orderBook;

    public Shop() {
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive");
    }
   
    @Override
    public ICustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    @Override
    public IOrderBook getOrderBook() {
        return orderBook;
    }

    @Override
    public IProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }

    
     // If we have no database we can use this
    // @PostConstruct, cant use with test
    // Not testes possible need UserTransaction utx
    private void initTestData() {

        // Add new data
        productCatalogue.create(new Product("banana", 11));
        productCatalogue.create(new Product("apple", 22));
        productCatalogue.create(new Product("pear", 33));
        productCatalogue.create(new Product("pineapple", 44));
        
        productCatalogue.create(new Product("orange", 55));
        productCatalogue.create(new Product("blackberry", 66));
        productCatalogue.create(new Product("blueberry", 77));
        productCatalogue.create(new Product("avocado", 88));
        
        productCatalogue.create(new Product("apricot", 99));
        productCatalogue.create(new Product("lemon", 100));
        productCatalogue.create(new Product("mango", 110));
        productCatalogue.create(new Product("melon", 120));
        
        productCatalogue.create(new Product("plum", 130));
        productCatalogue.create(new Product("satsuma", 140));
        productCatalogue.create(new Product("nectarine", 150));
        productCatalogue.create(new Product("lime", 160));
        
        productCatalogue.create(new Product("grape", 170));
        productCatalogue.create(new Product("fig", 180));

        customerRegistry.create(new Customer(new Address("aaa", 1, "aaa"),
                "arne", "arnesson", "arne@gmail.com"));
        customerRegistry.create(new Customer(new Address("bbbb", 2, "bbb"),
                "berit", "beritsson", "berit@gmail.com"));
        customerRegistry.create(new Customer(new Address("ccc", 3, "ccc"),
                "cecilia", "ceciliasson", "cecila@gmail.com"));

        Customer c = customerRegistry.getByName("arne").get(0);
        for (Product p : productCatalogue.findRange(0, 2)) {
            c.addProductToCart(p);
        }
        orderBook.create(new PurchaseOrder(c, c.getCart().getAsOrderItems()));

    }
}

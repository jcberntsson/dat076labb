package edu.chl.hajo.shop.core;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing the persistence layer
 *
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database) GlassFish not needed using Arquillian
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestShopPersistence {

    @Inject
    Shop shop;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "shop.war")
                // Add all classes
                .addPackage("edu.chl.hajo.shop.core")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }

    @Test
    public void testPersistAProduct() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        List<Product> ps = shop.getProductCatalogue().findAll();
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }
    
    @Test
    public void testProductGetByName() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        List<Product> ps = shop.getProductCatalogue().getByName("aaa");
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }
    
    @Test
    public void testPurchaseOrder() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        
        Product pp = new Product("bbb", 123);
        shop.getProductCatalogue().create(pp);
        
        PurchaseOrder po = new PurchaseOrder();
        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem oi = new OrderItem();
        oi.setProduct(shop.getProductCatalogue().findAll().get(0));
        OrderItem oi2 = new OrderItem();
        oi2.setProduct(shop.getProductCatalogue().findAll().get(1));
        items.add(oi);
        items.add(oi2);
        po.setItems(items);
        
        shop.getOrderBook().create(po);
        assertTrue(shop.getOrderBook().findAll().get(0).equals(po));
        shop.getOrderBook().delete(po.getId());
        
    }
    

    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    @PersistenceContext(unitName = "jpa_shop_test_pu")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {  
        utx.begin();  
        em.joinTransaction();
        em.createQuery("delete from PurchaseOrder").executeUpdate();
        em.createQuery("delete from Customer").executeUpdate();
        em.createQuery("delete from Product").executeUpdate();
        utx.commit();
    }

}

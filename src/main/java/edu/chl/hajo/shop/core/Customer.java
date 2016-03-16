package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 A Customer

 @author hajo
 */
@Entity
public class Customer extends AbstractEntity {

    @Setter
    @Getter
    private transient Cart cart = new Cart();
    private Address address;
    @Setter
    @Getter
    private String fname;
    @Setter
    @Getter
    private String lname;
    @Setter
    @Getter
    private String email;

    public Customer() {
    }

    public Customer(Address address, String fname,
            String lname, String email) {
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public Customer(Long id, Address address, String fname,
            String lname, String email) {
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void addProductToCart(Product product) {
        cart.add(product);
    }

    public void removeProductFromCart(Product product) {
        cart.remove(product);
    }

    public void emptyCart() {
        cart = new Cart();
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + getId() + ", address=" + address + ", "
                + "fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }
}

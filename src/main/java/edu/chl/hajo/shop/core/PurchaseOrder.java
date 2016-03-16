package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 A purchase order

 @author hajo
 */
@Entity
public class PurchaseOrder extends AbstractEntity {

    // State of order
    public enum State {

        ACCEPTED,
        CANCELLED,
        INVOICED,
        UNINVOIDED,
        SHIPPED,
    }
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date orderDate = new Date(); // Avoid SQL99 reserved word
    @Getter
    @Setter
    private List<OrderItem> items;
    @Getter
    @Setter

    private Customer customer;
    private State orderState = State.ACCEPTED;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public PurchaseOrder(Long id, Customer customer, List<OrderItem> items) {
        super(id);
        this.customer = customer;
        this.items = items;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + orderDate
                + ", items=" + items + ", customer=" + customer
                + ", state=" + orderState + '}';
    }
}

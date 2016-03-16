package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.core.Customer;
import edu.chl.hajo.shop.core.OrderItem;
import edu.chl.hajo.shop.core.PurchaseOrder.State;
import edu.chl.hajo.shop.persistence.AbstractEntity_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2016-03-10T14:40:11")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ extends AbstractEntity_ {

    public static volatile SingularAttribute<PurchaseOrder, Date> orderDate;
    public static volatile ListAttribute<PurchaseOrder, OrderItem> items;
    public static volatile SingularAttribute<PurchaseOrder, Customer> customer;
    public static volatile SingularAttribute<PurchaseOrder, State> orderState;

}
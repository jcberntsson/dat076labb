package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.core.Product;
import edu.chl.hajo.shop.persistence.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2016-03-16T10:02:28")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ extends AbstractEntity_ {

    public static volatile SingularAttribute<OrderItem, Product> product;
    public static volatile SingularAttribute<OrderItem, Integer> quantity;

}
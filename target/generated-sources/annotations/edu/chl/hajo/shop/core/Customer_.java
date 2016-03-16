package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.core.Address;
import edu.chl.hajo.shop.persistence.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2016-03-10T14:40:11")
@StaticMetamodel(Customer.class)
public class Customer_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Customer, String> fname;
    public static volatile SingularAttribute<Customer, String> lname;
    public static volatile SingularAttribute<Customer, Address> address;
    public static volatile SingularAttribute<Customer, String> email;

}
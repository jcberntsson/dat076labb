package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 Interface to customer registry

 @author hajo
 */

public interface ICustomerRegistry extends IDAO<Customer, Long> {
    List<Customer> getByName(String name);
}

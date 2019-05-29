package app.base.model;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * 
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 */
@Component
public class CustomerTypeFormatter implements Formatter<CustomerType> {

    private final CustomerRepository customers;


    @Autowired
    public CustomerTypeFormatter(CustomerRepository customers) {
        this.customers = customers;
    }

    @Override
    public String print(CustomerType customersType, Locale locale) {
        return  customersType.getName();
    }

    @Override
    public CustomerType parse(String text, Locale locale) throws ParseException {
        Collection<CustomerType> findCustomerTypes = this.customers.findCustomerTypes();
        for (CustomerType type : findCustomerTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}

package app.base.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

/*

 * CREATE TABLE accounts(
id INTEGER IDENTITY PRIMARY KEY,
currency_type_id INTEGER NOT NULL,
ballance DOUBLE NOT NULL,
customer_id INTEGER NOT NULL
);
 * 
 * 
 * */

 
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @Column(name = "ballance")
    private Double ballance;
    
    public Double getBallance() {
        return this.ballance;
    }

    public void setBallance(Double value) {
        this.ballance = value;
    } 
 

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(CurrencyType type) {
        this.currencyType = type;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}

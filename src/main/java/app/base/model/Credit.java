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

@Entity
@Table(name = "credits")
public class Credit extends NamedEntity {

    @Column(name = "take_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate takeDate;
    
    @Column(name = "return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Customer customer;

    
    public void setTakeDate(LocalDate takeDate) {
        this.takeDate = takeDate;
    }

    public LocalDate getTakeDate() {
        return this.takeDate;
    }
    
    public void setReturnDateDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }



}

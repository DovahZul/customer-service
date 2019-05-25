package app.base.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
public class Customer extends Person {
	
	@Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "mobile_phone")
    @NotEmpty
    private String mobilePhone;

    @Column(name = "passport_serial")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String passportSerial;
    
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @Column(name = "status")
    @NotEmpty
    private String status;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "customer")
    private Set<Account> accounts;
    
    @ManyToOne
    @JoinColumn(name = "workers_id")
    private Worker worker;
    
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CustomerType type;
    
    public CustomerType getType() {
        return this.type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getMobilePhone() {
    	return this.mobilePhone;
    }
    
    public void setMobilePhone(String value) {
    	this.mobilePhone = value;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    
    
    public String getPassportSerial() {
    	return this.passportSerial;
    }
    
    public void setPassportSerial(String value) {
    	this.passportSerial = value;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public void setAddress(String value) {
    	this.address = value;
    }
    
    
    
    public void setWorker(Worker value) {
    	this.worker = value;
    }
    public Worker getWorker() {
    	return this.worker;
    }
    
    public String getStatus() {
    	return this.status;
    }
    
    public void setStatus(String value) {
    	this.status = value;
    }
    
  
    
    
    
    public Set<Account> getAccountsInternal() {
        if (this.accounts == null) {
            this.accounts = new HashSet<>();
        }
        return this.accounts;
    }

    protected void setAccountsInternal(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        List<Account> sortedAccounts = new ArrayList<>(getAccountsInternal());
        PropertyComparator.sort(sortedAccounts,
                new MutableSortDefinition("id", true, true));
        return Collections.unmodifiableList(sortedAccounts);
    }

    public void addAccount(Account account) {
        if (account.isNew()) {
        	getAccountsInternal().add(account);
        }
        account.setCustomer(this);
    }
   
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("address", this.address)
                .append("passport_serial", this.getPassportSerial())
                .append("mobile_phone", this.mobilePhone)
                .append("status", this.getStatus())
                .append("worker", this.getWorker())
                .append("type", this.getType())
                .toString();
    }
}

/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

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
import org.springframework.samples.petclinic.model.Person;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
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
    private PetType type;
    
    public PetType getType() {
        return this.type;
    }

    public void setType(PetType type) {
        this.type = type;
    }
    
    //@ManyToOne
    //@JoinColumn(name = "type_id")
    //private PetType type;

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
    
  
    
    
    
    protected Set<Account> getAccountsInternal() {
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
    

    

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    /*
    public Account getAccount(Integer id) {
        return getAccount(id, true);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    /*
    public Credit getCredits(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Credit credit : getCreditsInternal()) {
            if (!ignoreNew || !credit.isNew()) {
                String compName = credit.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return credit;
                }
            }
        }
        return null;
    }
    */

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

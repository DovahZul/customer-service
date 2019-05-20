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

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data.
 * See: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface CustomerRepository extends Repository<Customer, Integer> {

    /**
     * Retrieve {@link Owner}s from the data store by last name, returning all owners
     * whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching {@link Owner}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT DISTINCT customer FROM Customer customer left join fetch customer.accounts WHERE customer.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<Customer> findByLastName(@Param("lastName") String lastName);

    /**
     * Retrieve an {@link Owner} from the data store by id.
     * @param id the id to search for
     * @return the {@link Owner} if found
     */
    @Query("SELECT customer FROM Customer customer left join fetch customer.accounts WHERE customer.id =:id")
    @Transactional(readOnly = true)
    Customer findById(@Param("id") Integer id);

    
    //to fix: ALTER TABLE accounts ADD CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customers (id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Customer customer WHERE customer.id =:cust_id") //do not left spaces between : and values ! >_<
	void deleteById(@Param("cust_id") Integer id);
    
    @Query("SELECT customer FROM Customer customer")
    @Transactional(readOnly = true)
    Collection<Customer> getAll();
    
    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<PetType> findPetTypes();
    
    @Query("SELECT worker FROM Worker worker ORDER BY worker.firstName")
    @Transactional(readOnly = true)
    List<Worker> findWorkers();
    
    //@Query("SELECT worker FROM Worker worker ORDER BY worker.fistName")
    //@Transactional(readOnly = true)
    //List<Worker> findWorkers();

    /**
     * Save an {@link Owner} to the data store, either inserting or updating it.
     * @param owner the {@link Owner} to save
     */
    void save(Customer customer);

    @Query("SELECT account FROM Account account WHERE customer.id = :id")
    @Transactional(readOnly = true)
	List<Account> findAccounts(@Param("id") Integer id);
    
    
    

	


}

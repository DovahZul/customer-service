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

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data.
 * See: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface AccountRepository extends Repository<Account, Integer> {

    /**
     * Retrieve all {@link CustomerType}s from the data store.
     * @return a Collection of {@link CustomerType}s.
     */
    @Query("SELECT acctype FROM CurrencyType acctype ORDER BY acctype.name")
    @Transactional(readOnly = true)
    List<CurrencyType> findCurrencyTypes();

    /**
     * Retrieve a {@link Pet} from the data store by id.
     * @param id the id to search for
     * @return the {@link Pet} if found
     */
    @Transactional(readOnly = true)
    Account findById(Integer id);
    
    //ALTER TABLE credits ADD CONSTRAINT fk_credit_account FOREIGN KEY (account_id) REFERENCES accounts (id);
    //ALTER TABLE deposits ADD CONSTRAINT fk_deposit_account FOREIGN KEY (account_id) REFERENCES accounts (id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Account account WHERE customer_id =:cust_id AND id =:acc_id ") //do not left spaces between : and values ! >_<
	void deleteAccountForCustomerIdById(@Param("cust_id") Integer cust_id, @Param("acc_id") Integer acc_id);

    /**
     * Save a {@link Pet} to the data store, either inserting or updating it.
     * @param pet the {@link Pet} to save
     */
    void save(Account account);

}


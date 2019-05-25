package app.base.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends Repository<Customer, Integer> {

    @Query("SELECT DISTINCT customer FROM Customer customer left join fetch customer.accounts WHERE customer.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<Customer> findByLastName(@Param("lastName") String lastName);

    @Query("SELECT customer FROM Customer customer left join fetch customer.accounts WHERE customer.id =:id")
    @Transactional(readOnly = true)
    Customer findById(@Param("id") Integer id);

    
    //to fix: ALTER TABLE accounts ADD CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customers (id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Customer customer WHERE customer.id =:cust_id") //do not left spaces between : and values ! >_<
	void deleteById(@Param("cust_id") Integer id);
    
    @Query("SELECT customer FROM Customer customer order by customer.firstName")
    @Transactional(readOnly = true)
    Collection<Customer> getAll();
    
    @Query("SELECT customertype FROM CustomerType customertype ORDER BY customertype.name")
    @Transactional(readOnly = true)
    List<CustomerType> findCustomerTypes();
    
    @Query("SELECT worker FROM Worker worker ORDER BY worker.firstName")
    @Transactional(readOnly = true)
    List<Worker> findWorkers();
    
    //@Query("SELECT worker FROM Worker worker ORDER BY worker.fistName")
    //@Transactional(readOnly = true)
    //List<Worker> findWorkers();

    void save(Customer customer);

    @Query("SELECT account FROM Account account WHERE customer.id = :id")
    @Transactional(readOnly = true)
	List<Account> findAccounts(@Param("id") Integer id);
    
    
    

	


}

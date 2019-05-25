package app.base.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AccountRepository extends Repository<Account, Integer> {


    @Query("SELECT acctype FROM CurrencyType acctype ORDER BY acctype.name")
    @Transactional(readOnly = true)
    List<CurrencyType> findCurrencyTypes();
    
    @Transactional(readOnly = true)
    Account findById(Integer id);
    
    //ALTER TABLE credits ADD CONSTRAINT fk_credit_account FOREIGN KEY (account_id) REFERENCES accounts (id);
    //ALTER TABLE deposits ADD CONSTRAINT fk_deposit_account FOREIGN KEY (account_id) REFERENCES accounts (id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Account account WHERE customer_id =:cust_id AND id =:acc_id ") //do not left spaces between : and values ! >_<
	void deleteAccountForCustomerIdById(@Param("cust_id") Integer cust_id, @Param("acc_id") Integer acc_id);

    void save(Account account);

}


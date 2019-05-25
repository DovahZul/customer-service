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
public class CurrencyTypeFormatter implements Formatter<CurrencyType> {

    private final AccountRepository accounts;


    @Autowired
    public CurrencyTypeFormatter(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @Override
    public String print(CurrencyType accountType, Locale locale) {
        return accountType.getName();
    }

    @Override
    public CurrencyType parse(String text, Locale locale) throws ParseException {
        Collection<CurrencyType> findAccountTypes = this.accounts.findCurrencyTypes();
        for (CurrencyType type : findAccountTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}

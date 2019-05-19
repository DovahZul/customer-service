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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/customers/{customerId}")
class AccountController {

    private static final String VIEWS_ACC_CREATE_OR_UPDATE_FORM = "accounts/createOrUpdateAccountForm";
    private final AccountRepository accounts;
    private final CustomerRepository customers;

    public AccountController(AccountRepository accs, CustomerRepository customers) {
        this.accounts = accs;
        this.customers = customers;
    }

    @ModelAttribute("currencyTypes")
    public Collection<CurrencyType> populateAccountTypes() {
        return this.accounts.findCurrencyTypes();
    }

    @ModelAttribute("customer")
    public Customer findCustomer(@PathVariable("customerId") int customerId) {
        return this.customers.findById(customerId);
    }

    @InitBinder("customer")
    public void initCustomerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

   // @InitBinder("account")
  //  public void initPetBinder(WebDataBinder dataBinder) {
   //     dataBinder.setValidator(new PetValidator());
   // }

    @GetMapping("/accounts/new")
    public String initCreationForm(Customer customer, ModelMap model) {
        Account account = new Account();
        //account.setCustomer(customer);
        customer.addAccount(account);
        model.put("account", account);
        return VIEWS_ACC_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/accounts/new")
    public String processCreationForm(Customer customer, @Valid Account account, BindingResult result, ModelMap model) {
    	customer.addAccount(account);
    	if (result.hasErrors()) {
            return VIEWS_ACC_CREATE_OR_UPDATE_FORM;
        } else {
        	model.put("account", account);
            this.accounts.save(account);
            return "redirect:/customers/" + customer.getId();
        }
    }

    @GetMapping("/accounts/{accountId}/edit")
    public String initUpdateForm(@PathVariable("accountId") int accountId, ModelMap model) {
        Account account = this.accounts.findById(accountId);
        model.put("account", account);
        return VIEWS_ACC_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/accounts/{accountId}/edit")
    public String processUpdateForm(@Valid Account account, BindingResult result, Customer customer, ModelMap model) {
        if (result.hasErrors()) {
        	account.setCustomer(customer);
            model.put("account", account);
            return VIEWS_ACC_CREATE_OR_UPDATE_FORM;
        } else {
            customer.addAccount(account);
            this.accounts.save(account);
            return "redirect:/customers/{customerId}";
        }
    }

}

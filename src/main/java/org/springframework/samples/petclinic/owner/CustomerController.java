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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
class CustomerController {
	
	private Integer currentDiaplayedCustomerId = 0;

    private static final String VIEWS_CUSTOMERS_CREATE_OR_UPDATE_FORM = "customers/createOrUpdateCustomerForm";
    private final CustomerRepository customers;


    public CustomerController(CustomerRepository bankService) {
        this.customers = bankService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/customers/new")
    public String initCreationForm(Map<String, Object> model, Model m) {
        Customer customer = new Customer();
        model.put("customer", customer);
        m.addAttribute("customerDetailsActive","active");
        return VIEWS_CUSTOMERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/customers/new")
    public String processCreationForm(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CUSTOMERS_CREATE_OR_UPDATE_FORM;
        } else {
            this.customers.save(customer);
            return "redirect:/customers/" + customer.getId();
        }
    }

    @GetMapping("/customers/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("customer", new Customer());
        return "customers/findCustomers";
    }

    @GetMapping("/")
    public String processFindForm(Customer customer, BindingResult result, Map<String, Object> model, Model m) {

        // allow parameterless GET request for /owners to return all records
        if (customer.getLastName() == null) {
        	customer.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Customer> results = this.customers.getAll();
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "customers/findCustomers";
        } else if (results.size() == 1) {
            // 1 owner found
        	customer = results.iterator().next();
            return "redirect:/customers/" + customer.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            m.addAttribute("homePageActive","active");
            return "homepage";
        }
    }

    @GetMapping("/customers/{customerId}/edit")
    public String initUpdateCustomerForm(@PathVariable("customerId") int customerId, Model model) {
        Customer customer = this.customers.findById(customerId);
        model.addAttribute(customer);
        model.addAttribute("customerDetailsActive","active");
        return VIEWS_CUSTOMERS_CREATE_OR_UPDATE_FORM;    
    }

    @PostMapping("/customers/{customerId}/edit")
    public String processUpdateCustomerForm(@Valid Customer customer, BindingResult result, @PathVariable("customerId") int customerId, Model m) {
        if (result.hasErrors()) {
            return VIEWS_CUSTOMERS_CREATE_OR_UPDATE_FORM;
        } else {
        	customer.setId(customerId);
            this.customers.save(customer);
            m.addAttribute("customerDetailsActive","active");
            return "redirect:/customers/{customerId}";
        }
        
    }
    
    
    
    @ModelAttribute("types")
    public Collection<CustomerType> populateCustomerTypes() {
        return customers.findCustomerTypes();
    }
    
    @ModelAttribute("workers")
    public List<Worker> populateWorkers() {
    	return customers.findWorkers();
    }
    
    //@GetMapping("/customers/{customerId}")
    
    @RequestMapping(value = "/customers/{usr_id}", method = RequestMethod.POST)
    @ModelAttribute("accounts{usr_id}")
    //@PathVariable Person person
    public List<Account> populateAccounts(@PathVariable("usr_id") int cust_id){
    	System.out.println("GET CUSTOMER ID: " + cust_id);
    	return customers.findAccounts(cust_id);
    	
    }
    

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/customers/{customerId}")
    public ModelAndView showCustomer(@PathVariable("customerId") int customerId, Model m) {
    	
        ModelAndView mav = new ModelAndView("customers/customerDetails");
        mav.addObject(this.customers.findById(customerId));
        mav.addObject(this.customers.findById(customerId).getAccountsInternal());
        System.out.println("Current displayed customer id: " + currentDiaplayedCustomerId);
        for(Account acc : this.customers.findById(customerId).getAccountsInternal())
        {
        	System.out.println("Account: id[" + acc.getId() + "] " + acc.getCurrencyType()+" : "+acc.getBallance()+"\n");
        	
        }
        System.out.println("MAV: " + mav.getModel().toString() + "\n");
        this.currentDiaplayedCustomerId = customerId;
        m.addAttribute("customerDetailsActive","active");
        return mav;
    }
    
    
    @GetMapping("/customers/{customerId}/delete")
    public String deleteCustomer(@PathVariable("customerId") int customerId, Customer customer, BindingResult result, Map<String, Object> model) {

        this.customers.deleteById(customerId);
        
       

        // find owners by last name
        Collection<Customer> results = this.customers.getAll();
        model.put("selections", results);
        return "homepage";
    }

}
    
        

    
    



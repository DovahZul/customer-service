package org.springframework.samples.petclinic.owner;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
 
/**
 * @author Crunchify.com
 * 
 */
 
@Controller
public class CrunchifySpringAjaxJQuery {
	
	  private final CustomerRepository customers;
	  private final HouseRepository houses;


	    public CrunchifySpringAjaxJQuery(CustomerRepository bankService, HouseRepository nedvijimost) {
	        this.customers = bankService;
	        this.houses = nedvijimost;
	    }
	    

    @RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
 
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    Collection<House> getTime() {
 
        Random rand = new Random();  
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        String result2 = new String("{'name': 'name1','score':'30'},{'name': 'name2','score':'50'}");
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return houses.getAll();
    }
}




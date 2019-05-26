package app.base.controller;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.base.model.Customer;
import app.base.model.CustomerRepository;
import app.base.model.House;
import app.base.model.HouseRepository;

import java.util.Random;

import javax.validation.Valid;
 
@Controller
public class SpringAjaxJQueryController {
	
	  private final CustomerRepository customers;
	  private final HouseRepository houses;


	    public SpringAjaxJQueryController(CustomerRepository bankService, HouseRepository nedvijimost) {
	        this.customers = bankService;
	        this.houses = nedvijimost;
	    }
	    

	    @RequestMapping( method=RequestMethod.GET, value="/qqq" )
	    public String getMainView( ) {        
	        /* do all your normal stuff here to build your primary NON-ajax view
	         * in the same way you always do
	         */    
	    	System.out.println("AjaxExampleController call");
	    	return "ajaxexample";
	    }
 
    @RequestMapping(method=RequestMethod.GET, value="/subView")
    public @ResponseBody
    Collection<House> getHouses( Model m) {
    	
    	System.out.println("Responce from JQuery controller!");
 
        Random rand = new Random();  
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        String result2 = new String("{'name': 'name1','score':'30'},{'name': 'name2','score':'50'}");
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller..");
        System.out.println("Houses List...");
        //m.addAttribute("houses", houses.getAll());
       ///m.addAttribute("qwe", "qwe");
        //return new ModelAndView( "ajaxexample" );
        for(House item : houses.getAll())
        {
        	System.out.println(item.getKadastr());
        	System.out.println(item.getAddress());
        	System.out.println(item.getBase());
        	System.out.println(item.getComment());
        	System.out.println(item.getDateDoc());
        	System.out.println(item.getFioHost());
        	System.out.println(item.getDistrict());
        	System.out.println(item.getLand());
        	System.out.println(item.getMaterial());
        	System.out.println(item.getPassport());
        	System.out.println(item.geFlats());
        	System.out.println(item.getBalcony());
        	System.out.println(item.getBranch());
        	System.out.println(item.getBorn());
        	System.out.println(item.getDwell());
        	System.out.println(item.getElevator());
        	System.out.println(item.getFlow());
        	System.out.println(item.getHeight());
        	System.out.println(item.getLine());
        	System.out.println(item.getPart());
        	System.out.println(item.getRecord());
        	System.out.println(item.getRooms());
        	System.out.println(item.getSquare());
        	System.out.println(item.getSquareFlat());
        	System.out.println(item.getStorey());
        	System.out.println(item.getWear());
        	System.out.println(item.getYear());
        	System.out.println("___________________");
        }
        return houses.getAll();
    }
    
    @GetMapping("/subView/new")
    public String initCreationForm(Map<String, Object> model, Model m) {
        House house = new House();
        model.put("house", house);
        return "houseForm";
    }
    
    @PostMapping("/subView/new")
    public String processCreationForm(@Valid House house, BindingResult result) {
        if (result.hasErrors()) {
            return "ajaxexample";
        } else {
            this.houses.save(house);
            System.out.println("Added new house: " + house.getAddress());
            return "redirect:/qqq";
        }
    }
    
    @RequestMapping("/search")
    @ResponseBody
    public  Collection<House> searchPost(@RequestParam("term") String query) {

        //List<Object> retVal = getListOfObjectFromDbBasedOnQuery(query);
    	System.out.println("Performed search by value: "+ query);
    	for(House item : houses.getSearchMaches(query))
        {
        	System.out.println(item.getKadastr());
        	System.out.println(item.getAddress());
        	System.out.println(item.getBase());
        	System.out.println(item.getComment());
        	System.out.println(item.getDateDoc());
        	System.out.println(item.getFioHost());
        	System.out.println(item.getDistrict());
        	System.out.println(item.getLand());
        	System.out.println(item.getMaterial());
        	System.out.println(item.getPassport());
        	System.out.println(item.geFlats());
        	System.out.println(item.getBalcony());
        	System.out.println(item.getBranch());
        	System.out.println(item.getBorn());
        	System.out.println(item.getDwell());
        	System.out.println(item.getElevator());
        	System.out.println(item.getFlow());
        	System.out.println(item.getHeight());
        	System.out.println(item.getLine());
        	System.out.println(item.getPart());
        	System.out.println(item.getRecord());
        	System.out.println(item.getRooms());
        	System.out.println(item.getSquare());
        	System.out.println(item.getSquareFlat());
        	System.out.println(item.getStorey());
        	System.out.println(item.getWear());
        	System.out.println(item.getYear());
        	System.out.println("___________________");
        }
        return houses.getSearchMaches(query);
    }
}




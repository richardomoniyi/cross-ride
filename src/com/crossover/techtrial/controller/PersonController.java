/**
 * 
 */
package com.crossover.techtrial.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.PersonService;
import com.crossover.techtrial.service.RideService;

/**
 * 
 * @author crossover
 */

@RestController
public class PersonController {
  
  @Autowired
  PersonService personService;
  RideService rideService;
  
  @PostMapping(path = "/api/person")
  public ResponseEntity<Person> register(@RequestBody Person p) {
	  
    return ResponseEntity.ok(personService.save(p));
  }
  @PostMapping(path = "/api/ride")
  public ResponseEntity<Person> submitRide(@RequestBody Ride r) {
	  //check if the Rider and Person are valid entity 
	  Person person = personService.findById(r.getDriver().getId());
	    if (person == null) {
	    	return ResponseEntity.notFound().build();
	    }
	    person = personService.findById(r.getRider().getId());
	    if (person == null) {
	    	return ResponseEntity.notFound().build();
	    }
	    
	    try {
			//r.getEndTime();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
			//parse the date string to convert it into java.util.date object
			Date startDate = dateFormatter.parse(r.getStartTime());
			Date endDate = dateFormatter.parse(r.getEndTime());
			
			if(startDate.after(endDate) ||  startDate.equals(endDate)){
				return ResponseEntity.notFound().build();
			}
			//
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    //check if driver already has at least 1 ride running
	    //rideService.
	    rideService.save(r);
	    
   
    return null;
  }
  @GetMapping(path = "/api/top5driver")
  public ResponseEntity<List<Person>> getTop5Persons() {
    return ResponseEntity.ok(personService.getTop5Person());
  }
  @GetMapping(path = "/api/person")
  public ResponseEntity<List<Person>> getAllPersons() {
    return ResponseEntity.ok(personService.getAll());
  }
  
  @GetMapping(path = "/api/person/{perso-id}")
  public ResponseEntity<Person> getPersonById(@PathVariable(name="person-id", required=true)Long personId) {
    Person person = personService.findById(personId);
    if (person != null) {
      return ResponseEntity.ok(person);
    }
    return ResponseEntity.notFound().build();
  }
  
}

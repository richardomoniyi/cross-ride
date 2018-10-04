/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.List;
import com.crossover.techtrial.model.Person;

/**
 * PersonService interface for Persons.
 * @author cossover
 *
 */
public interface PersonService {
  public List<Person> getAll();
  
  public List<Person> getTop5Person();
  
  public Person save(Person p);
  
  public Person findById(Long personId);
  
}

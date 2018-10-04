/**
 * 
 */
package com.crossover.techtrial.repositories;

import com.crossover.techtrial.model.Ride;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {
public Optional<Ride> findById(long rideId);
}
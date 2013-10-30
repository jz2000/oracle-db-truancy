package org.jz.truancy.oradb;

import java.util.List;

public interface ConnectionService {

	/**
	 * Retrieve all cars in the catalog.
	 * @return all cars
	 */
	public List<Connection> findAll();
	
	/**
	 * search cars according to keyword in model and make.
	 * @param keyword for search
	 * @return list of car that match the keyword
	 */
	public List<Connection> search(String keyword);
}

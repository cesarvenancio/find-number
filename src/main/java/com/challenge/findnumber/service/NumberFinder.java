package com.challenge.findnumber.service;
import java.util.List;

import com.challenge.findnumber.exception.FindNumberException;
import com.challenge.findnumber.vo.CustomNumberEntity;

public interface NumberFinder {
	
	/**
	 * Method called to find number inside json file
	 * @param number
	 * @param filePath
	 * @throws FindNumberException 
	 */
	void findNumber(int number, String filePath) throws FindNumberException;

	/**
	 * Checks if valueToFind is contained in the list
	 * @param valueToFind
	 * @param list
	 * @return true if valueToFind exist in the list
	 *          false if not
	 */
	boolean contains(int valueToFind, List<CustomNumberEntity> list);
	
	/**
	 * Read a list of CustommNumberEntity from a file 
	 * In the file the list is as a JSON  object
	 * @param filePath
	 * @return a list of CustomNumberEntity objects read from the file.
	 * @throws FindNumberException 
	 */
	List<CustomNumberEntity> readFromFile(String filePath) throws FindNumberException;

}


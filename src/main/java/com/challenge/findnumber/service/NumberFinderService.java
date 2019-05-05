package com.challenge.findnumber.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.challenge.findnumber.comparator.FastestComparator;
import com.challenge.findnumber.exception.FindNumberException;
import com.challenge.findnumber.vo.CustomNumberEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class NumberFinderService implements NumberFinder {

	private static final Logger logger = Logger.getLogger(NumberFinderService.class.getName());
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[- +]?\\d*$");

	/**
	 * Method called from App to find a specific number inside the JSON file
	 * @param number
	 * @param filePath
	 * @throws FindNumberException 
	 */
	public void findNumber(int number, String filePath) throws FindNumberException {

		List<CustomNumberEntity> numbers = readFromFile(filePath);

		logger.log(Level.INFO, "Searching...");
		
		if (contains(number, numbers)) {
			logger.log(Level.INFO, "Json contains number {0} ", number);
		} else {
			logger.log(Level.INFO, "Not contains {0}", number);
		}
	}

	/**
	 * Method to read the JSON and transform in List<CustomNumbeEntity>
	 * @param filePath
	 * @return List<CustomNumberEntity>
	 * @throws FindNumberException 
	 */
	public List<CustomNumberEntity> readFromFile(String filePath) throws FindNumberException {

		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
			
			return mapper.readValue(br, new TypeReference<List<CustomNumberEntity>>() {});
		} catch (NoSuchFileException e) {
			logger.log(Level.SEVERE, "File does not exist {0}", e.getMessage());
			throw new FindNumberException(String.format("File does not exist %s", e.getMessage()), e);
		} catch (MismatchedInputException e) {
			logger.log(Level.SEVERE, "Invalid json file");
			throw new FindNumberException("Invalid json file", e);
		} catch (JsonParseException e) {
			logger.log(Level.SEVERE, "Invalid json format");
			throw new FindNumberException("Invalid json format", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new FindNumberException(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Method to find the number inside the List<CustomNumbeEntity> 
	 * The filters inside this method could be done by Jackson when read file, decided to let here in case the list can be used in other places
	 * @param valueToFind
	 * @param List<CustomNumberEntity> list
	 * @return boolean contains
	 * @throws FindNumberException 
	 */
	public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
		return list.parallelStream()
				.filter(custom -> Objects.nonNull(custom.getNumber()))
				.filter(custom -> NUMBER_PATTERN.matcher(custom.getNumber()).matches())
				.anyMatch(custom -> FastestComparator.compare(valueToFind, custom) == 0);
	}

}

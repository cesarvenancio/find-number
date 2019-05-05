package com.challenge.findnumber;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.challenge.findnumber.exception.FindNumberException;
import com.challenge.findnumber.service.NumberFinderService;
import com.challenge.findnumber.vo.CustomNumberEntity;

/**
 * Customer Tests
 */
public class FindNumberTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	private static NumberFinderService numberFinderService = new NumberFinderService();

	@Test
	public void whenFileNotExistThenException() throws FindNumberException {
		
		String filePath = "notExist";
		
		expectedEx.expect(FindNumberException.class);
		expectedEx.expectMessage(String.format("File does not exist %s", "notExist"));
		
		numberFinderService.findNumber(2, filePath);
	}
	
	@Test
	public void shouldFindNumberInJsonFile() throws FindNumberException {
		
		String filePath = "src/test/resources/numbers.json";

		List<CustomNumberEntity> numbers = numberFinderService.readFromFile(filePath); 
		assertTrue(numberFinderService.contains(99, numbers));
	}
	
	@Test
	public void shouldNotFindNumberInJsonFile() throws FindNumberException {
		
		String filePath = "src/test/resources/numbers.json";

		List<CustomNumberEntity> numbers = numberFinderService.readFromFile(filePath); 
		assertFalse(numberFinderService.contains(88, numbers));
	}
	
	@Test
	public void whenIsInvalidJsonFileThenException() throws FindNumberException {
		
		String filePath = "src/test/resources/INVALID_FILE.json";

		expectedEx.expect(FindNumberException.class);
		expectedEx.expectMessage("Invalid json file");
		
		numberFinderService.readFromFile(filePath); 
	}
	
	@Test
	public void whenIsInvalidJsonFormatThenException() throws FindNumberException {
		
		String filePath = "src/test/resources/INVALID_FORMAT.json";

		expectedEx.expect(FindNumberException.class);
		expectedEx.expectMessage("Invalid json format");
		
		numberFinderService.readFromFile(filePath); 
	}
	
}

package com.challenge.findnumber;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.challenge.findnumber.exception.FindNumberException;
import com.challenge.findnumber.service.NumberFinderService;

public class AppMain {

	static Logger logger = Logger.getLogger(AppMain.class.getName());
	static NumberFinderService numberFinderService = new NumberFinderService();

	/**
	 * Method main
	 */
	public static void main(String[] args) {
		String filePath = null;

		try (Scanner scan = new Scanner(System.in)) {

			if (args.length > 0) {
				filePath = args[0];
			} else {
				filePath = "src/main/resources/numbers.json";
			}

			while (scan.hasNext()) {
				int number = getNumber(scan);
				numberFinderService.findNumber(number, filePath);
			}
		} catch (FindNumberException e) {
			logger.log(Level.SEVERE, String.format("Error processing file %s. Detail: %s", filePath, e.getMessage()),
					e);
			e.printStackTrace();
		}
	}

	public static int getNumber(Scanner scan) throws FindNumberException {
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			logger.log(Level.SEVERE, "Invalid number");
			throw new FindNumberException("Invalid number", e);
		}
	}
}

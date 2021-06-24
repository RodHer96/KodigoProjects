package main;

import validators.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Aircraft;
import models.Airline;
import models.Country;
import models.Flight;
import input.ExcelFileInput;
import input.ExcelFileInputAircraft;
import input.ExcelFileInputAirline;
import input.ExcelFileInputCountry;
import output.*;
import utils.*;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static List<Flight> flights = new ArrayList<Flight>();
	static List<Aircraft> aircrafts = new ArrayList<Aircraft>();
	static List<Airline> airlines = new ArrayList<Airline>();
	static List<Country> countries = new ArrayList<Country>();
	static String excelFilePath = "Inputs.xlsx";
	
	public static void initializeData() {
		ExcelFileInputAircraft excelFileInputAircrafts = new ExcelFileInputAircraft();
		ExcelFileInputAirline excelFileInputAirlines = new ExcelFileInputAirline();
		ExcelFileInputCountry excelFileInputCountry = new ExcelFileInputCountry();
		
		try {
			aircrafts = excelFileInputAircrafts.readAircraftsFromExcelFile(excelFilePath);
			airlines = excelFileInputAirlines.readAirlineFromExcelFile(excelFilePath);
			countries = excelFileInputCountry.readCountryFromExcelFile(excelFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		ExcelFileInput excelFileInputFlights = new ExcelFileInput();
		boolean cont = true;
		int mainMenuOption;
		int flightAction;
		initializeData();
		System.out.println("----------WELCOME----------");
		while(cont) {
			System.out.println("What would you like to do?\n1. Input Data\n2. Update a Flight\n3. Send information of a Flight or Flights\n4. Show Flights\n5. Show Catalog of Aircrafts\n6. Exit");
			mainMenuOption = InputValidator.inputValidatorMainMenu(sc.nextInt());
			switch (mainMenuOption) {
			case 1:
				System.out.println("Would you like to type the data or to read them through an Excel File?\n1. Keyboard Input\n2. Excel File Input");
				if(InputValidator.inputValidator(sc.nextInt()) == 1) {
					
				}else {
					try {
						flights = excelFileInputFlights.readFlightsFromExcelFile(excelFilePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				if(!flights.isEmpty()) {
					System.out.println("-----These are the flights-----");
					ShowFlights.showFlights(flights);
					System.out.println("Which one would you like to update? (Type the number of the flight you would like to update): ");
					int flightToUpdate = InputValidator.inputValidatorFlightToUpdate(sc.nextInt(), flights.size());
					System.out.println("What would you like to do?\n1. Delay the flight\n2. Report an incident\n3. Cancel the flight");
					flightAction = InputValidator.inputValidatorActionFlight(sc.nextInt());
					switch (flightAction) {
					case 1:
						sc.nextLine();
						System.out.println("Type the new Date of arrival:");
						String date = sc.nextLine();
						System.out.println("Type the reason of the delay:");
						String delay = sc.nextLine();
						flights.get(flightToUpdate-1).delayFlight(date, delay);
						break;
					case 2:
						sc.nextLine();
						System.out.println("What incident would you like to report?");
						String incident = sc.nextLine();
						flights.get(flightToUpdate-1).reportIncident(incident);
						break;
					case 3:
						sc.nextLine();
						System.out.println("Why did the flight cancel?");
						String reason = sc.nextLine();
						flights.get(flightToUpdate-1).cancelFlight(reason);
						break;
					}
				}else {
					System.out.println("There are no flights registered.");
				}
				break;
			case 3:
				System.out.println("How would you like the report to be sorted by?\n1. By a specific date\n2. By a specific flight\n3. All of them");
				int sortType = InputValidator.inputValidatorExcelSortBy(sc.nextInt());
				if(sortType == 1) {
					System.out.println("Please, type the date from which the report is going to be sorted by: (format: Thu, Dec 07 1998)");
					sc.nextLine();
					String dateSort = sc.nextLine();
					List<Flight> flightsSortedByDate = Flight.sortByDate(dateSort, flights);
					WriteExcelFileOutput.writeOutput(flightsSortedByDate);
					IMailImplementation emailImp = new IMailImplementation();
					try {
						emailImp.sendMailWithAttachment(emailImp.getSession(), "rodmayi_@hotmail.com", "Flights", "Here are the flights sorted by Date!", "Flights.xls");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(sortType == 2){
					ShowFlights.showFlights(flights);
					System.out.println("Please, type the number of the flight you want to know more about: ");
					int flightSort = InputValidator.inputValidatorFlightToUpdate(sc.nextInt(), flights.size());
					List<Flight> flightsSortedByFlight = new ArrayList<Flight>();
					flightsSortedByFlight.add(flights.get(flightSort-1));
					WriteExcelFileOutput.writeOutput(flightsSortedByFlight);
					IMailImplementation emailImp = new IMailImplementation();
					try {
						emailImp.sendMailWithAttachment(emailImp.getSession(), "rodmayi_@hotmail.com", "Flights", "Here is the flight!", "Flights.xls");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					WriteExcelFileOutput.writeOutput(flights);
					IMailImplementation emailImp = new IMailImplementation();
					try {
						emailImp.sendMailWithAttachment(emailImp.getSession(), "rodmayi_@hotmail.com", "Flights", "Here are the flights!", "Flights.xls");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 4:
				System.out.println("What type of flights would you like to see?\n1. All of them\n2. Ontime\n3. Delayed\n4. Cancelled");
				int flightType = InputValidator.inputValidatorFlightType(sc.nextInt());
				switch(flightType) {
				case 1:
					System.out.println("---------------");
					if(!flights.isEmpty()) {
						ShowFlights.showFlights(flights);
					}else {
						System.out.println("There are no flights yet!");
					}
					break;
				case 2:
					System.out.println("---------------");
					ShowFlights.showOntimeFlights(flights);
					break;
				case 3:
					System.out.println("---------------");
					ShowFlights.showDelayedFlights(flights);
					break;
				case 4:
					System.out.println("---------------");
					ShowFlights.showCancelledFlights(flights);
					break;
				}
				break;
			case 5:
				if(!aircrafts.isEmpty()) {
					ShowCatalogAircrafts.showCatalog(aircrafts);
				}else {
					System.out.println("There are no aircrafts yet!");
				}
				break;
			case 6:
				break;
			default:
				break;
			}
			if(mainMenuOption == 6) {break;}
			System.out.println("Would you like to do another action?\n1. Yes\n2. No");
			cont = ContinueValidator.continueValidator(sc.nextInt());
		}
		System.out.println("The End...");
	}

}

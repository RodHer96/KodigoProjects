package input;

import models.Flight;
import models.Aircraft;
import models.Airline;
import models.Country;
import models.City;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFileInput {
	public List<Flight> readFlightsFromExcelFile(String excelFilePath) throws IOException {
	    List<Flight> listFlights = new ArrayList<Flight>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Flight flight = new Flight();
	        Aircraft aircraft = new Aircraft();
	        Airline airline = new Airline();
	        Country countryFrom = new Country();
	        Country countryTo = new Country();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	 
	            switch (columnIndex) {
	            case 0:
	                flight.setFlightNumber(String.valueOf(flight.getCellValue(nextCell)));
	                break;	 
	            case 1:
	                airline.setAirlineName((String) airline.getCellValue(nextCell)); 
	                flight.setAirline(airline);
	                break;
	            case 2:
	            	aircraft.setModel((String) aircraft.getCellValue(nextCell));
	            	break;
	            case 3:
	            	aircraft.setPassengerCapacity(Double.valueOf(String.valueOf(aircraft.getCellValue(nextCell))));
	            	break;
	            case 4:
	            	aircraft.setFullTankReach((Double) aircraft.getCellValue(nextCell));
	            	flight.setAircraft(aircraft);
	            	break;
	            case 5:
	            	countryFrom.setCountryName((String) flight.getCellValue(nextCell));
	            	break;
	            case 6:
	            	City cityFrom = new City();
	            	cityFrom.setCityName((String) flight.getCellValue(nextCell));
	            	countryFrom.setCity(cityFrom);
	            	flight.setCountryFrom(countryFrom);
	            	break;
	            case 7: 
	            	countryTo.setCountryName((String) flight.getCellValue(nextCell));	            	
	            	break;
	            case 8:
	            	City cityTo = new City();
	            	cityTo.setCityName((String) flight.getCellValue(nextCell));
	            	countryTo.setCity(cityTo);
	            	flight.setCountryTo(countryTo);
	            	break;
	            case 9:
	            	flight.setDepartureDate((String) flight.getCellValue(nextCell));
	            	break;
	            case 10:
	            	flight.setArrivalDate((String) flight.getCellValue(nextCell));
	            	flight.setStatus("Ontime");
	            	break;
	            }
	            
	 
	        }
	        listFlights.add(flight);
	    }
	 
	    workbook.close();
	    inputStream.close();
	    System.out.println("Excel File has been successfully read!");
	    return listFlights;
	}
}

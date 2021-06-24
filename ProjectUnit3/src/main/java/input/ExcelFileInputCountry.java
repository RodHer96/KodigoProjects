package input;

import models.Country;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import models.Aircraft;
import models.City;

public class ExcelFileInputCountry {
	public List<Country> readCountryFromExcelFile(String excelFilePath) throws IOException {
	    List<Country> listCountries = new ArrayList<Country>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(3);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Country country = new Country();	
	        City city = new City();
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	            switch (columnIndex) {
	            case 0:
	                city.setCityName((String) country.getCellValue(nextCell)); 
	                break;
	            case 1:
	            	country.setCountryName((String) country.getCellValue(nextCell));
	            	country.setCity(city);
	            	break;
	            }
	        }
	        listCountries.add(country);
	    }
	    workbook.close();
	    inputStream.close();
	    return listCountries;
	}
}

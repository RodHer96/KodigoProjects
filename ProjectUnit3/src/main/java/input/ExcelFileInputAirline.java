package input;

import models.Aircraft;
import models.Airline;

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

public class ExcelFileInputAirline {
	public List<Airline> readAirlineFromExcelFile(String excelFilePath) throws IOException {
	    List<Airline> listAirlines = new ArrayList<Airline>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(2);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Airline airline = new Airline();	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	            switch (columnIndex) {
	            case 0:
	                airline.setAirlineName((String) airline.getCellValue(nextCell)); 
	                break;
	            }
	        }
	        listAirlines.add(airline);
	    }
	 
	    workbook.close();
	    inputStream.close();
	 
	    return listAirlines;
	}
}

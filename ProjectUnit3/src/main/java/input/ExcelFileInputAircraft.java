package input;

import models.Aircraft;

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

public class ExcelFileInputAircraft {
	public List<Aircraft> readAircraftsFromExcelFile(String excelFilePath) throws IOException {
	    List<Aircraft> listAircrafts = new ArrayList<Aircraft>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(1);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Aircraft aircraft = new Aircraft();	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	            switch (columnIndex) {
	            case 0:
	                aircraft.setModel((String) aircraft.getCellValue(nextCell)); 
	                break;
	            case 1:
	            	aircraft.setPassengerCapacity(Double.valueOf(String.valueOf(aircraft.getCellValue(nextCell))));
	            	break;
	            case 2:
	            	aircraft.setFullTankReach((Double) aircraft.getCellValue(nextCell));
	            	break;
	            }
	        }
	        listAircrafts.add(aircraft);
	    }
	 
	    workbook.close();
	    inputStream.close();
	 
	    return listAircrafts;
	}
}

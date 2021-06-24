package models;

import org.apache.poi.ss.usermodel.Cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor  
public class Airline {
	private String airlineName;
	
	public Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case STRING:
	        return cell.getStringCellValue();
	 
	    case BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case NUMERIC:
	        return cell.getNumericCellValue();
	    }
	    return null;
	}
}

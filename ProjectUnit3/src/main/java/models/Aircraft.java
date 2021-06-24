package models;

import org.apache.poi.ss.usermodel.Cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Aircraft {
	private String model;
	private Double passengerCapacity;
	private Double fullTankReach;
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("-Model: "); str.append(model);
		str.append("\n-Passengers Capacity: "); str.append(passengerCapacity);
		str.append("\n-Full Tank Reach: "); str.append(fullTankReach); str.append(" KM");
		return str.toString();
	}
	
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

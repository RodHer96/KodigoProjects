package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor
public class Flight {
	
	private Country countryFrom;
	private Country countryTo;
	private Airline airline;
	private Aircraft aircraft;
	private String flightNumber;
	private String incidentOnBoard;
	private String status;
	private String departureDate;
	private String arrivalDate;
	private String cancellationReason;
	private String reasonDelay;
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("-Flight Number: "); str.append(this.getFlightNumber());
		str.append("\n-Airline: "); str.append(this.getAirline().getAirlineName());
		str.append("\n-Aircraft: "); str.append(this.getAircraft().getModel()); str.append(" -Passenger Capacity: "); str.append(this.getAircraft().getPassengerCapacity()); str.append(" -Full Tank Reach: "); str.append(this.getAircraft().getFullTankReach());
		str.append("\n-From: "); str.append(this.getCountryFrom().getCity().getCityName()); str.append(", "); str.append(this.getCountryFrom().getCountryName());
		str.append("\n-To: "); str.append(this.getCountryTo().getCity().getCityName()); str.append(", "); str.append(this.getCountryTo().getCountryName());
		str.append("\n-Departure Date: "); str.append(this.getDepartureDate()); str.append("\n-Arrival Date: "); str.append(this.getArrivalDate());
		str.append("\n--Status: "); str.append(this.getStatus());
		if(this.getStatus() == "Landed") {
			str.append("\n-Incidents: "); str.append(this.getIncidentOnBoard());
		}
		if(this.getStatus() == "Cancelled") {
			str.append("\n-Cancelled due to: "); str.append(this.getCancellationReason());
		}
		if(this.getStatus() == "Delayed") {
			str.append("\n-Delayed due to: "); str.append(this.getReasonDelay());
		}
		return str.toString();
	}
	
	public void delayFlight(String date, String reasonDelayed) {
		this.setArrivalDate(date);
		this.setReasonDelay(reasonDelayed);
		this.setStatus("Delayed");
	}
	
	public void reportIncident(String incident) {
		this.setIncidentOnBoard(incident);
		this.status = "Landed";
	}
	
	public void cancelFlight(String reasonCancelled) {
		this.setCancellationReason(reasonCancelled);
		this.setDepartureDate("-");
		this.setArrivalDate("-");
		this.status = "Cancelled";
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
	
	public static List<Flight> sortByDate(String date, List<Flight> flights) {
		List<Flight> res = new ArrayList<Flight>();
		for(Flight f : flights) {
			if(f.getDepartureDate().contains(date)) {
				res.add(f);
			}
		}
		return res;
	}
	
	public static List<Flight> sortByFlight(String flightNumber, List<Flight> flights) {
		List<Flight> res = new ArrayList<Flight>();
		for(Flight f : flights) {
			if(f.getFlightNumber() == "9.0") {
				res.add(f);
			}
		}
		return res;
	}
}

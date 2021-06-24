package output;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import models.Flight;

public class WriteExcelFileOutput {
	public static void writeOutput(List<Flight> flights) {
		Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < flights.size(); i++) {
            Row row = sheet.createRow(i);

            Cell cellIdFlight = row.createCell((short) 0);
            RichTextString idFlight = new HSSFRichTextString(flights.get(i).getFlightNumber());
            cellIdFlight.setCellValue(idFlight);

            Cell cellAirline = row.createCell((short) 1);
            RichTextString airlineFlight = new HSSFRichTextString(flights.get(i).getAirline().getAirlineName());
            cellAirline.setCellValue(airlineFlight);

            Cell cellModel = row.createCell((short) 2);
            RichTextString modelFlight = new HSSFRichTextString(flights.get(i).getAircraft().getModel());
            cellModel.setCellValue(modelFlight);

            Cell cellOrigin = row.createCell((short) 3);
            RichTextString originFlight = new HSSFRichTextString(flights.get(i).getCountryFrom().getCity().getCityName());
            cellOrigin.setCellValue(originFlight);

            Cell cellDestination = row.createCell((short) 4);
            RichTextString destinationFlight = new HSSFRichTextString(flights.get(i).getCountryTo().getCity().getCityName());
            cellDestination.setCellValue(destinationFlight);

            Cell cellDepartureDate = row.createCell((short) 5);
            RichTextString departureDateFlight = new HSSFRichTextString(flights.get(i).getDepartureDate());
            cellDepartureDate.setCellValue(departureDateFlight);

            Cell cellArrivalDate = row.createCell((short) 6);
            RichTextString arrivalDateFlight;
            if (flights.get(i).getArrivalDate() != null) {
                arrivalDateFlight = new HSSFRichTextString(flights.get(i).getArrivalDate());
            } else {
                arrivalDateFlight = new HSSFRichTextString("---");
            }
            cellArrivalDate.setCellValue(arrivalDateFlight);

            Cell cellIncidents = row.createCell((short) 7);
            RichTextString incidentsFlight;
            if (flights.get(i).getIncidentOnBoard() != null) {
                incidentsFlight = new HSSFRichTextString(flights.get(i).getIncidentOnBoard());
            } else {
                incidentsFlight = new HSSFRichTextString("---");
            }
            cellIncidents.setCellValue(incidentsFlight);

            Cell cancellationReason = row.createCell((short) 8);
            RichTextString cancellationFlight;
            if (flights.get(i).getCancellationReason() != null) {
                cancellationFlight = new HSSFRichTextString(flights.get(i).getCancellationReason());
            } else {
                cancellationFlight = new HSSFRichTextString("---");
            }
            cancellationReason.setCellValue(cancellationFlight);
            
            Cell delayReason = row.createCell((short) 9);
            RichTextString delayFlight;
            if (flights.get(i).getReasonDelay() != null) {
                delayFlight = new HSSFRichTextString(flights.get(i).getReasonDelay());
            } else {
                delayFlight = new HSSFRichTextString("---");
            }
            delayReason.setCellValue(delayFlight);
        }
        try {
            FileOutputStream file = new FileOutputStream("Flights.xls");
            workbook.write(file);
            file.close();
            System.out.println("Excel File created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

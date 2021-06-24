package validators;

import java.util.Scanner;

public class InputValidator {
	static Scanner sc = new Scanner(System.in);
	public static int inputValidator(int opc) {
		int op = opc;
		while(op != 1 && op != 2) {
			System.out.println("Please, type 1 if you want to type or 2 if you want to read the data from an Excel File");
			op = sc.nextInt();
		}
		return op;
	}
	public static int inputValidatorMainMenu(int opc) {
		int op = opc;
		while(op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6) {
			System.out.println("Please, type 1, 2, 3, 4, 5 or 6");
			op = sc.nextInt();
		}
		return op;
	}
	
	public static int inputValidatorFlightToUpdate(int opc, int limit) {
		int op = opc;
		while(op <= 0 || op > limit) {
			System.out.println("Please, type a number which is in the list");
			op = sc.nextInt();
		}
		return op;
	}
	
	public static int inputValidatorActionFlight(int opc) {
		int op = opc;
		while(op != 1 && op != 2 && op != 3) {
			System.out.println("Please, type 1, 2, or 3");
			op = sc.nextInt();
		}
		return op;
	}
	
	public static int inputValidatorFlightType(int opc) {
		int op = opc;
		while(op != 1 && op != 2 && op != 3 && op != 4) {
			System.out.println("Please, type 1, 2, 3 or 4");
			op = sc.nextInt();
		}
		return op;
	}
	
	public static int inputValidatorExcelSortBy(int opc) {
		int op = opc;
		while(op != 1 && op != 2 && op != 3) {
			System.out.println("Please, type 1, 2 or 3");
			op = sc.nextInt();
		}
		return op;
	}
}

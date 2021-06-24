package validators;

import java.util.Scanner;

public class ContinueValidator {
	static Scanner sc = new Scanner(System.in);
	public static boolean continueValidator(int opc) {
		int op = opc;
		while(op != 1 && op != 2) {
			System.out.println("Please, type 1 if you want to do another action or 2 if you do not");
			op = sc.nextInt();
		}
		boolean res = (op == 1)? true : false;
		return res;
	}
}

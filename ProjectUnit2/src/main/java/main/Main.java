package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.mail.Session;
import interfaces.IMail;
import outputs.*;
public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static boolean menu = true;
	public static int subOption;
	public static int inputMeth;
	public static DecimalFormat df;
	public static Subject physics = new Subject();
	public static Subject math = new Subject();
	public static Subject chemistry = new Subject();
	public static List<Student> studentsPhysics = new ArrayList<Student>();
	public static List<Student> studentsMath = new ArrayList<Student>();
	public static List<Student> studentsChemistry = new ArrayList<Student>();
	public static PdfWrite pdf = new PdfWrite();
	public static IMail emailUtill = new IMailImplementation();
	
	//To modify the numbers' format
	public static String format(double n) {
		df = new DecimalFormat("#.##");
		return df.format(n);
	}
			
	//This method verifies if the user wants to do another action
	public static void anotherAction() {
	    menu = true;
		int anotherAction;
		System.out.println("Will there be something else?\n 1. Yes\n 2. No");
		anotherAction = sc.nextInt();
		while(anotherAction != 1 && anotherAction != 2) {
			System.out.println("Please, type 1 or 2");
			anotherAction = sc.nextInt();
		}
		sc.nextLine();
		if(anotherAction == 1) menu = true;
		else menu = false;
	}
	
	//This method verifies the subject that the user wants to work on
	public static int subjectOption() {
		System.out.println("Please, choose the subject you want to work on:\n 1. Physics\n 2. Math\n 3. Chemistry");
		int res = sc.nextInt();
		while(res != 1 && res != 2 && res != 3) {
			System.out.println("Please, type:\n 1. Physics\n 2. Math\n or 3. Chemistry");
			sc.nextInt();
		}
		return res;
	}
	
	//This method verifies the action the user wants to run: 1. Keyboard input 2. File text input
	public static void inputMethod() {
		System.out.println("Select the input method:\n 1. Keyboard input\n 2. Filetext input");
		inputMeth = sc.nextInt();
		while(inputMeth != 1 && inputMeth != 2) {
			System.out.println("Please, select a valid input method:\n 1. Keyboard input\n 2. Filetext input");
			inputMeth = sc.nextInt();
		}
	}
	
	//Keyboard input for Physics
	public static void keyboardInputPhysics() {
		System.out.println("How many students are?");
		int n = sc.nextInt();
		for (int i = 1; i <= n ; i++) { 
			System.out.println("Type the name of the student " + i + ", please");
			String name = sc.next();
			System.out.println("Type the score of the student " + i + ", please");
			Double score = sc.nextDouble();
			while(score < 0 || score > 10) {
				System.out.println("Please, type a valid score");
				score = sc.nextDouble();
			}
			if(physics.getFrecuency().containsKey(score)) {
				physics.getFrecuency().put(score, physics.getFrecuency().get(score) + 1);
			}else {
				physics.getFrecuency().put(score, 1);
			}
			Student student = new Student(name, score);
			studentsPhysics.add(student);
		}
		physics.setNameSubject("Physics"); physics.setStudents(studentsPhysics);
	}
	
	//Keyboard input for Math
	public static void keyboardInputMath() {
		System.out.println("How many students are?");
		int n = sc.nextInt();
		for (int i = 1; i <= n ; i++) { 
			System.out.println("Type the name of the student " + i + ", please");
			String name = sc.next();
			System.out.println("Type the score of the student " + i + ", please");
			Double score = sc.nextDouble();
			while(score < 0 || score > 10) {
				System.out.println("Please, type a valid score");
				score = sc.nextDouble();
			}
			if(math.getFrecuency().containsKey(score)) {
				math.getFrecuency().put(score, math.getFrecuency().get(score) + 1);
			}else {
				math.getFrecuency().put(score, 1);
			}
			Student student = new Student(name, score);
			studentsMath.add(student);
		}
		math.setNameSubject("Math"); math.setStudents(studentsMath);
	}	
	//Keyboard input for Chemistry
	public static void keyboardInputChemistry() {
		System.out.println("How many students are?");
		int n = sc.nextInt();
		for (int i = 1; i <= n ; i++) { 
			System.out.println("Type the name of the student " + i + ", please");
			String name = sc.next();
			System.out.println("Type the score of the student " + i + ", please");
			Double score = sc.nextDouble();
			while(score < 0 || score > 10) {
				System.out.println("please, type a valid score");
				score = sc.nextDouble();
			}
			if(chemistry.getFrecuency().containsKey(score)) {
				chemistry.getFrecuency().put(score, chemistry.getFrecuency().get(score) + 1);
			}else {
				chemistry.getFrecuency().put(score, 1);
			}
			Student student = new Student(name, score);
			studentsChemistry.add(student);
		}
		chemistry.setNameSubject("Chemistry"); chemistry.setStudents(studentsChemistry);
	}	
	
	//This method allow us to read the text file for Physics
	public static void readFileTextPhysics(String archive) throws FileNotFoundException, IOException {
        String name;
        Double score;
        FileReader f = new FileReader(archive);
        BufferedReader b = new BufferedReader(f);
        while((name = b.readLine())!=null) {
            score = Double.parseDouble(b.readLine());
            Student student = new Student(name, score);
			studentsPhysics.add(student);
			if(physics.getFrecuency().containsKey(score)) {
				physics.getFrecuency().put(score, physics.getFrecuency().get(score) + 1);
			}else {
				physics.getFrecuency().put(score, 1);
			}
        }
        physics.setNameSubject("Physics"); physics.setStudents(studentsPhysics);
        b.close();
    }
	
	//This method allow us to read the text file for Math
	public static void readFileTextMath(String archive) throws FileNotFoundException, IOException {
        String name;
        Double score;
        FileReader f = new FileReader(archive);
        BufferedReader b = new BufferedReader(f);
        while((name = b.readLine())!=null) {
            score = Double.parseDouble(b.readLine());
            Student student = new Student(name, score);
			studentsMath.add(student);
			if(math.getFrecuency().containsKey(score)) {
				math.getFrecuency().put(score, math.getFrecuency().get(score) + 1);
			}else {
				math.getFrecuency().put(score, 1);
			}
        }
        math.setNameSubject("Math"); math.setStudents(studentsMath);
        b.close();
    }
	
	//This method allow us to read the text file for Chemistry
	public static void readFileTextChemistry(String archive) throws FileNotFoundException, IOException {
		String name;
        Double score;
        FileReader f = new FileReader(archive);
        BufferedReader b = new BufferedReader(f);
        while((name = b.readLine())!=null) {
            score = Double.parseDouble(b.readLine());
            Student student = new Student(name, score);
			studentsChemistry.add(student);
			if(chemistry.getFrecuency().containsKey(score)) {
				chemistry.getFrecuency().put(score, chemistry.getFrecuency().get(score) + 1);
			}else {
				chemistry.getFrecuency().put(score, 1);
			}
        }
        chemistry.setNameSubject("Chemistry"); chemistry.setStudents(studentsChemistry);
        b.close();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(menu) {
			subOption = subjectOption();
			switch (subOption) {
			case 1:
				System.out.println("Welcome to Physics");
				inputMethod();
				if(inputMeth == 1) keyboardInputPhysics();
				else {
					try {
						readFileTextPhysics("PhysicsStudentsInput.txt");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				physics.calMinScore(); physics.calMaxScore(); physics.calAvg(); physics.calMostFrecuency(); 
				System.out.println("-------OUTPUTS-------");
				System.out.println(physics.getMinScore() + "------" +  physics.getMaxScore() + "------" + format(physics.getAverage()));
				System.out.println("The most repeated scores are: ");
				for (Map.Entry<Double, Integer> entry : physics.getFrecuency().entrySet()) {
				    if(entry.getValue() == physics.getMostFrecuency()) {
				    	System.out.println(entry.getKey());
				    }
				}
				pdf.writeUsingIText("Physics.pdf", physics.getStudents(), physics);
				Session session = emailUtill.getSession();
				emailUtill.sendReport(new File("Physics.pdf"), session,"Profesor", emailUtill.EMAIL,"These are the statistics for the subject: Physics!");
				break;
			case 2:
				System.out.println("Welcome to Math");
				inputMethod();
				if(inputMeth == 1) keyboardInputMath();
				else {
					try {
						readFileTextMath("MathStudentsInput.txt");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				math.calMinScore(); math.calMaxScore(); math.calAvg(); math.calMostFrecuency();
				System.out.println("-------OUTPUTS-------");
				System.out.println(math.getMinScore() + "------" + math.getMaxScore() + "------" + format(math.getAverage()));
				System.out.println("The most repeated scores are: ");
				for (Map.Entry<Double, Integer> entry : math.getFrecuency().entrySet()) {
				    if(entry.getValue() == math.getMostFrecuency()) {
				    	System.out.println(entry.getKey());
				    }
				}
				pdf.writeUsingIText("Math.pdf", math.getStudents(), math);
				Session session1 = emailUtill.getSession();
				emailUtill.sendReport(new File("Math.pdf"), session1,"Profesor", emailUtill.EMAIL,"These are the statistics for the subject: Math!");
			break;
			case 3:
				System.out.println("Welcome to Chemistry");
				inputMethod();
				if(inputMeth == 1) keyboardInputChemistry();
				else {
					try {
						readFileTextChemistry("ChemistryStudentsInput.txt");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				chemistry.calMinScore(); chemistry.calMaxScore(); chemistry.calAvg(); chemistry.calMostFrecuency();
				System.out.println("-------OUTPUTS-------");
				System.out.println(chemistry.getMinScore() + "------" + chemistry.getMaxScore() + "------" + format(chemistry.getAverage()));
				System.out.println("The most repeated scores are: ");
				for (Map.Entry<Double, Integer> entry : chemistry.getFrecuency().entrySet()) {
				    if(entry.getValue() == chemistry.getMostFrecuency()) {
				    	System.out.println(entry.getKey());
				    }
				}
				pdf.writeUsingIText("Chemistry.pdf", chemistry.getStudents(), chemistry);
				Session session2 = emailUtill.getSession();
				emailUtill.sendReport(new File("Chemistry.pdf"), session2,"Profesor", emailUtill.EMAIL,"These are the statistics for the subject: Chemistry!");
			break;
			default:
				break;
			}
			studentsPhysics.clear(); studentsMath.clear(); studentsChemistry.clear(); physics.getFrecuency().clear(); math.getFrecuency().clear(); chemistry.getFrecuency().clear();
			anotherAction(); 
			System.out.println("--------------");
		}
		System.out.println("Exit");
	}

}

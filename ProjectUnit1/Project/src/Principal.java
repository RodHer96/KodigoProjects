/*
 * Proyeto Unidad I
 * Nombre del autor: Rodrigo Torres
 * Descripci�n del proyecto: Programa para calcular ciertas estad�sticas de un grupo de diez alumnos.
 * */
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Principal {

	private static ArrayList<Student> alumnos = new ArrayList<Student>();
	private static Map<Double, Integer> frecuencia = new HashMap<Double, Integer>();
	private static Scanner sc = new Scanner(System.in);
	private static DecimalFormat df;
	private static Double notaMin;
	private static Double notaMax;
	private static Double prom;
	private static Double notaMasRepe;
	private static Double notaMenRepe;
	
	//M�todo que lee los datos ingresados y guarda el objeto
	public static void leerDatos(int n) {
		System.out.println("Ingrese el nombre del alumno " + n + ", por favor");
		String nom = sc.next();
		System.out.println("Ingrese el score del alumno " + n + ", por favor");
		Double score = sc.nextDouble();
		while(score < 0 || score > 10) {
			System.out.println("Por favor, ingrese una nota entre 0 y 10");
			score = sc.nextDouble();
		}
		if(frecuencia.containsKey(score)) {
			frecuencia.put(score, frecuencia.get(score) + 1);
		}else {
			frecuencia.put(score, 1);
		}
		Student estudiante = new Student(nom, score);
		alumnos.add(estudiante);
	}
	
	//M�todo que calcula la nota m�nima
	public static void calcularNotaMin() {
		Double aux = 10.0;
		Iterator<Student> it = alumnos.iterator();
		while(it.hasNext()){
			Student i = it.next();
		    if(i.getScore() < aux) {
		    	aux = i.getScore();
		    }
		}
		notaMin = aux;
	}
	
	//M�todo que calcula la nota m�xima
		public static void calcularNotaMax() {
			Double aux = 0.0;
			Iterator<Student> it = alumnos.iterator();
			while(it.hasNext()) {
				Student i = it.next();
			    if(i.getScore() > aux) {
			    	aux = i.getScore();
			    }
			}
			notaMax = aux;
		}
	
		//M�todo que calcula el promedio
		public static void calcularPromedio() {
			Double aux = 0.0;
			Iterator<Student> it = alumnos.iterator();
			while(it.hasNext()) {
				Student i = it.next();
				aux += i.getScore();
			}
			prom = aux / alumnos.size();
		}
		
		//M�todo para encontrar la nota m�s repetida
		public static Integer determinarMost() {
			Iterator iter = frecuencia.entrySet().iterator();
			Integer aux = 0;
			for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
			    if(entry.getValue() > aux) { 
			    	aux = entry.getValue(); 
			    	}
			}
			return aux;
		}
		
		//M�todo para encontrar la nota menos repetida
				public static Integer determinarLess() {
					Iterator iter = frecuencia.entrySet().iterator();
					Integer aux = 20;
					for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
					    if(entry.getValue() < aux) { 
					    	aux = entry.getValue(); 
					    	}
					}
					return aux;
				}
		
		//M�todo que imprime los resultados en consola
		public static void imprimirConsola() {
			Integer frecMost = determinarMost();
			Integer frecLess = determinarLess();
			Iterator iter = frecuencia.entrySet().iterator();
			System.out.println("-----Resultados-----");
			System.out.println("La nota m�xima obtenida fue " + notaMax);
			System.out.println("La nota m�nima obtenida fue " + notaMin);
			System.out.println("El promedio obtenido fue " + formatearNumero(prom));
			System.out.println("Las notas con la mayor frecuencia son: ");
			for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
			    if(entry.getValue() == frecMost) {
			    	System.out.println(entry.getKey());
			    }
			}
			System.out.println("Las notas con la menor frecuencia son: ");
			for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
			    if(entry.getValue() == frecLess) {
			    	System.out.println(entry.getKey());
			    }
			}
			System.out.println("Nota " + "----------" + " Frecuencia");
			for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
			    System.out.println(entry.getKey() + " ---------- " + entry.getValue());
			}
		}
		
		//M�todo que le da formato al par�metro
		public static String formatearNumero(double n) {
			df = new DecimalFormat("#.##");
			return df.format(n);
		}
		
		//M�todo que imprime los datos ingresados y los resultados en un fichero
		public static void imprimirFichero() {
			try {
				FileWriter fichero = new FileWriter("ResultadosProyecto1.txt");
				Integer frecMost = determinarMost();
				Integer frecLess = determinarLess();
				Iterator iter = frecuencia.entrySet().iterator();
				fichero.write("-----Datos ingresados-----\n");
				Iterator<Student> it = alumnos.iterator();
				while(it.hasNext()) {
					Student i = it.next();
				    fichero.write(i.getName() + " obtuvo una calificaci�n de " + i.getScore() + "\n");
				}
				fichero.write("-----Resultados-----\n");
				fichero.write("La nota m�xima obtenida fue " + notaMax + "\n");
				fichero.write("La nota m�nima obtenida fue " + notaMin + "\n");
				fichero.write("El promedio obtenido fue " + formatearNumero(prom) + "\n");
				fichero.write("Las notas con la mayor frecuencia son: \n");
				for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
				    if(entry.getValue() == frecMost) {
				    	fichero.write("->" + entry.getKey() + "\n");
				    }
				}
				fichero.write("Las notas con la menor frecuencia son: \n");
				for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
				    if(entry.getValue() == frecLess) {
				    	fichero.write("->" + entry.getKey() + "\n");
				    }
				}
				fichero.write("Nota " + "----------" + " Frecuencia" + "\n");
				for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
				    fichero.write(entry.getKey() + " ---------- " + entry.getValue() + "\n");
				}
				fichero.close();
			}catch(Exception ex) { ex.printStackTrace(); }
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 20 ; i++) { leerDatos(i); }
		calcularNotaMin();calcularNotaMax();calcularPromedio();
		imprimirConsola();imprimirFichero();
	}

}

package main;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Subject {
	private String nameSubject;
	private List<Student> students;
	private Double minScore;
	private Double maxScore;
	private Double average;
	private Integer mostFrecuency;
	private Map<Double, Integer> frecuency = new HashMap<Double, Integer>();
	
	public void calMinScore() {
		Double aux = 10.0;
		Iterator<Student> it = students.iterator();
		while(it.hasNext()){
			Student i = it.next();
		    if(i.getScore() < aux) {
		    	aux = i.getScore();
		    }
		}
		setMinScore(aux);
	}
	
	public void calMaxScore() {
		Double aux = 0.0;
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) {
			Student i = it.next();
		    if(i.getScore() > aux) {
		    	aux = i.getScore();
		    }
		}
		maxScore = aux;
	}
	
	public void calAvg() {
		Double aux = 0.0;
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) {
			Student i = it.next();
			aux += i.getScore();
		}
		average = aux / students.size();
	}
	
	public void calMostFrecuency() {
		Iterator iter = frecuency.entrySet().iterator();
		Integer aux = 0;
		for (Map.Entry<Double, Integer> entry : frecuency.entrySet()) {
		    if(entry.getValue() > aux) { 
		    	aux = entry.getValue(); 
		    	}
		}
		mostFrecuency = aux;
	}
	
	
	
}

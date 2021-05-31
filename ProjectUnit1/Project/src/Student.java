/*
 * Clase Estudiante.
 * */
public class Student {
	//Miembros de clase
	private String name;
	private Double score;
	
	//M�todos de la clase
	public Student(String name, Double score) {
		this.name = name;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
}

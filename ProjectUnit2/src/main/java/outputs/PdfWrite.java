package outputs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import main.Student;
import main.Subject;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;


public class PdfWrite {
	
	public static DecimalFormat df;
	
	public static String format(double n) {
		df = new DecimalFormat("#.##");
		return df.format(n);
	}
 
    public static void writeUsingIText(String fileName, List<Student> student, Subject subject) {
 
        Document document = new Document();
 
        try {
 
            PdfWriter.getInstance(document, new FileOutputStream(new File(fileName)));
 
            //open
            document.open();
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(16);
            f.setColor(new BaseColor(7, 220, 142));
            Paragraph p = new Paragraph("Subject: " + subject.getNameSubject(), f);
            p.setAlignment(Element.ALIGN_CENTER);
            
            document.add(p);
            
            Paragraph students = new Paragraph();
            Iterator<Student> it = student.iterator();
            students.add("The students are:\n "); //no alignment
            while(it.hasNext()) {
				Student i = it.next();
			    students.add(i.getNameStudent() + " got the score: " + i.getScore() + "\n");
			}
 
            document.add(students);
 
            Paragraph p2 = new Paragraph();
            p2.add("******Statistics******\n->The min score was: " + subject.getMinScore()); //no alignment
 
            document.add(p2);
            
            Paragraph p3 = new Paragraph();
            p3.add("->The max score was: " + subject.getMaxScore()); //no alignment
 
            document.add(p3);
            
            Paragraph p4 = new Paragraph();
            p4.add("->The average score was: " + format(subject.getAverage())); //no alignment
 
            document.add(p4);
            
            Paragraph p5 = new Paragraph();
            p5.add("->The most repeated scores are:\n ");
            for (Map.Entry<Double, Integer> entry : subject.getFrecuency().entrySet()) {
			    if(entry.getValue() == subject.getMostFrecuency()) {
			    	p5.add("\t->" + entry.getKey() + "\n");
			    }
			}
            
            document.add(p5);
 
            //close
            document.close();
 
            System.out.println("Done! PDF created successfully!");
         
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}

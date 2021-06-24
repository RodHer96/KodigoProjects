package interfaces;

import java.io.File;

import javax.mail.Session;

public interface IMail {
	
	public static final String EMAIL = "rodmayi_@hotmail.com";
	
	public String sendReport(File file, Session session, String subject, String to, String body);
	public Session getSession();
	
}

package interfaces;

import javax.mail.Session;
import java.io.UnsupportedEncodingException;

public interface IMail {
	public void sendMailWithAttachment(Session session, String emailTo, String subject, String body, String filename) throws UnsupportedEncodingException;
    public Session getSession();
}

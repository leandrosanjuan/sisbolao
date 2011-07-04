package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import config.Config;

public class EnviaEmail {

	public static void enviar(String assunto, String msg, String mail,
			 String nome) throws Exception {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setAuthenticator(new DefaultAuthenticator(Config.getProperty("admin"),
					Config.getProperty("emailSenha")));
			email.setSSL(true);
			email.setFrom(Config.getProperty("emailAdmin"),Config.getProperty("bolaoTitulo") );
			email.setSubject(assunto);
			email.setMsg(msg);
			email.addTo(mail,nome);
			email.send();
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}

	}
}

package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.services.persistence.PersistenceFacade;
import main.services.util.HashingPassword;
import main.services.util.Mail;

/**
 * 
 * @author Capici Alessandro
 *
 */
@SuppressWarnings("serial")
public class RegisterServlet extends MyServlet {
/**
 *  wee use this servlet when the user want to registe in our web site ,we need to know name , surname and password the user chose your password

 * @param name
 * @param path
 */
	public RegisterServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.html", true));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean flag = true;
		HashingPassword hashingPassword = new HashingPassword();
		
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String nome = request.getParameter("FirstName");
		String cognome = request.getParameter("LastName");
		String mail = request.getParameter("Email");
		String psw = request.getParameter("Password");
		String confpsw = request.getParameter("RipetiPassword");
		if (!psw.equals(confpsw)) {
			flag = false;
			response.getWriter().write(Rythm.render("sign-in.rtm", flag));
		}
		pf.addUser(nome, cognome, mail,hashingPassword.getHashPsw(psw), false);
		Mail m= new Mail(mail,nome);
		System.out.println("Email inviata"+ m);
		request.getSession().invalidate();
		response.sendRedirect("/login");
	}

}
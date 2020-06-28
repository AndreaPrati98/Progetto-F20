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
 * @author Capici Alessandro
 * 
 *         Servlet used to show register page. User needs to input his name,
 *         surname, password and email.
 */

@SuppressWarnings("serial")
public class RegisterServlet extends MyServlet {
	/**
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

	/**
	 * Manages post requests. Gets information sent using a post request from a
	 * form, if the two emails are different shows an error, otherwise
	 * a user will be added to db using PersistenceFacade methods.
	 * An email will be sent to the email chosen by the user.
	 * 
	 * @see PersistenceFacade
	 * @see Mail
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
		pf.addUser(nome, cognome, mail, hashingPassword.getHashPsw(psw), false);
		Mail m = new Mail(mail, nome);
		System.out.println("Email inviata" + m);
		request.getSession().invalidate();
		response.sendRedirect("/login");
	}

}
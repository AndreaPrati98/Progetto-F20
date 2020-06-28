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
	private static final String LOGIN = "/login";
	private static final String RIPETI_PASSWORD = "RipetiPassword";
	private static final String PASSWORD = "Password";
	private static final String EMAIL = "Email";
	private static final String LAST_NAME = "LastName";
	private static final String FIRST_NAME = "FirstName";
	private static final String SIGN_IN_HTML = "sign-in.html";

	/**
	 * @param name
	 * @param path
	 */
	public RegisterServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render(SIGN_IN_HTML, true));
	}

	/**
	 * Manages post requests. Gets information sent using a post request from a
	 * form, if the two emails are different shows an error, otherwise a user will
	 * be added to db using PersistenceFacade methods. An email will be sent to the
	 * email chosen by the user.
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
		String nome = request.getParameter(FIRST_NAME);
		String cognome = request.getParameter(LAST_NAME);
		String mail = request.getParameter(EMAIL);
		String psw = request.getParameter(PASSWORD);
		String confpsw = request.getParameter(RIPETI_PASSWORD);
		if (!psw.equals(confpsw)) {
			flag = false;
			response.getWriter().write(Rythm.render(SIGN_IN_HTML, flag));
		} else {
			pf.addUser(nome, cognome, mail, hashingPassword.getHashPsw(psw), false);
			Mail m = new Mail(mail, nome);
			request.getSession().invalidate();
			response.sendRedirect(LOGIN);
		}
	}

}
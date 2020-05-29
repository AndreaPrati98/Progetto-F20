package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import model.dao.PersistenceFacade;
import statics.Mail;
/**
 * 
 * @author Capici Alessandro
 *
 */
@SuppressWarnings("serial")
public class RegisterServlet extends MyServlet {

	public RegisterServlet(String name, String path) {
		super(name, path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.rtm",true));
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean flag=true;
		
		PersistenceFacade pf=PersistenceFacade.getIstance();
		String nome=request.getParameter("FirstName");
		String cognome=request.getParameter("LastName");
		String mail=request.getParameter("Email");
		String psw=request.getParameter("Password");
		String confpsw=request.getParameter("RipetiPassword");
		if (!psw.equals(confpsw)) {
			flag=false;
			response.getWriter().write(Rythm.render("sign-in.rtm",flag));
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(psw.getBytes("utf8"));
			psw=String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pf.addUser(nome, cognome, mail, psw);
		Mail m=new Mail(mail);
		response.getWriter().write(Rythm.render("sign-in.rtm",true));
	}

}
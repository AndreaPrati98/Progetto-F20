package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.SqliteDB;
import model.db.User;

public class GetRegisterServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String repeatPassword = request.getParameter("RipetiPassword");
		
		User user = new User(name, lastname, email, password);	
		SqliteDB db = new SqliteDB();
		db.insertUserToDB(user);
		System.out.println("Istruzione inserimento fatta");		
		request.setAttribute("nome", name);
		request.setAttribute("cognome", lastname);
		request.getRequestDispatcher("WEB-INF/datiregistrazionepostati.jsp").forward(request, response);;	
	}
	

}

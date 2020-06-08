package main.webapp.servlet;

import javax.servlet.http.HttpServlet;

/**
 * 
 * @author Capici Alessandro
 *
 */

@SuppressWarnings("serial")
public abstract class MyServlet extends HttpServlet {
	private String name;
	private String path;

	public MyServlet(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

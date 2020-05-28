package servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

/**
 * 
 * @author Capici Alessandro
 *
 */
public class ApplicationServer {
	private int port;
	private List<MyServlet> servlet;
	private Server server;

	public ApplicationServer(int port, List<MyServlet> servlet) {
		this.port = port;
		this.servlet = servlet;
	}

	public void start(){
		initTemplateEngine();
		server = new Server(port);
		ServletContextHandler handler = new ServletContextHandler();
		for (MyServlet servlet2 : servlet) {
			handler.addServlet(new ServletHolder(servlet2), servlet2.getPath());
		}
		addStaticFileServing(handler);
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop(){
		try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addStaticFileServing(ServletContextHandler handler) {
		ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
		holderPwd.setInitParameter("dirAllowed", "false");
		holderPwd.setInitParameter("pathInfoOnly", "true");
		handler.addServlet(holderPwd, "/util/*");
	}

	private void initTemplateEngine() {
		Map<String, Object> conf = new HashMap<>();
		conf.put("home.template", "view");
		Rythm.init(conf);
	}
}
package main.webapp.servlet;

import java.io.File;
import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;


public class ApplicationServer {
	private int port;
	private List<MyServlet> servlet;
	private Server server;
/**
 * this servlet class manage the connection by jetty (enable comunication )  we used rtm to write the web pages
 * for this reason wee had to make a handler for manage the session.

 * @param port
 * @param servlet
 */
	public ApplicationServer(int port, List<MyServlet> servlet) {
		this.port = port;
		this.servlet = servlet;
	}

	/**
	 * this methods need to generate a session end (by jetty) make to session handler
	 * this handler manage  only servlet (add ) 
	 * 
	 */
	public void start(){
		initTemplateEngine();
		server = new Server(port);
		
		FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
	    File baseDir = new File(System.getProperty("java.io.tmpdir"));
	    File storeDir = new File(baseDir, "javalin-session-store");
	    storeDir.mkdir();
	    fileSessionDataStore.setStoreDir(storeDir);
	
		
		SessionHandler sessionHandler = new SessionHandler();
	    SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
	    sessionCache.setSessionDataStore(fileSessionDataStore);
	    sessionHandler.setSessionCache(sessionCache);
	    sessionHandler.setHttpOnly(true);
		
		ServletContextHandler handler = new ServletContextHandler();		
		handler.setSessionHandler(sessionHandler);
		
		for (MyServlet servlet2 : servlet) {
			handler.addServlet(new ServletHolder(servlet2), servlet2.getPath());
		}
		addStaticFileServing(handler);
		server.setHandler(handler);		
		
		try {
			server.start();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void stop(){
		try {
			server.stop();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param handler
	 */

	private void addStaticFileServing(ServletContextHandler handler) {
		ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
		holderPwd.setInitParameter("resourceBase", "./Website");
		holderPwd.setInitParameter("dirAllowed", "false");
		holderPwd.setInitParameter("pathInfoOnly", "true");
		handler.addServlet(holderPwd, "/statics/*");
	}

	private void initTemplateEngine() {
		Map<String, Object> conf = new HashMap<>();
		conf.put("home.template", "main/webapp/website");
		Rythm.init(conf);
	}
}

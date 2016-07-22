package ranveer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class editTodoServlet extends HttpServlet {
	Key key=null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();

		String n = req.getParameter("name").trim();
		String d = req.getParameter("date").trim();
		if (n.equals("") || d.equals("")) {
			displayAlert("Invalid Input !", "'/editServlet'", out);
			return;
		}
		String uid = u.getUserId();
		PersistenceManager pm = null;
		Key user_key = KeyFactory.createKey("User", uid);
		ranveer.User user;
		try {
			pm = PMF.get().getPersistenceManager();
			Todo ap = pm.getObjectById(Todo.class, key);
			ap.setName(n);
			ap.setDate(d);
			pm.close();
			displayAlert("Todo Edited!", "'/'", out);

		} catch (Exception e) {
			displayAlert("Failure!", "'/'", out);
		} finally {
			pm.close();
		}
	}
	private void displayAlert(String msg, String path, PrintWriter out) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + msg + "');");
		out.println("location=" + path + ";");
		out.println("</script>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		key =(Key) req.getSession().getAttribute("update");

		try{
		if (u != null) {
			req.setAttribute("OK", 1);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Todo t = pm.getObjectById(Todo.class, key);
			req.setAttribute("name", t.getName());
			req.setAttribute("date", t.getDate());
			req.getSession().setAttribute("update",null);

		} else 
			req.setAttribute("OK", null);
		
		}catch(Exception e){resp.sendRedirect("/");}
		String login_url = us.createLoginURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/edit.jsp");
		rd.forward(req, resp);
	}

}

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
public class addTodoServlet extends HttpServlet {
	
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

		if (u != null ) {
			req.setAttribute("OK", 1);
		} else {
			req.setAttribute("OK", null);
		}
		String login_url = us.createLoginURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);

		RequestDispatcher req_dispatcher= req.getRequestDispatcher("/WEB-INF/add.jsp");
		req_dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		PrintWriter out = resp.getWriter();

		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();

		String n = req.getParameter("name").trim();
		String d = req.getParameter("date").trim();
		if (n.equals("") || d.equals("")) {
			displayAlert("Invalid Input Entered!", "'/addServlet'", out);
			return;
		}
		
		String user_id = u.getUserId();
		PersistenceManager pm = null;
		Key user_key = KeyFactory.createKey("User", user_id);
		ranveer.User user;
		Todo todo = new Todo(n,d);

		try {
			pm = PMF.get().getPersistenceManager();
			user = pm.getObjectById(ranveer.User.class, user_key);
			todo.setParent(user);
			user.addTodo(todo);
			pm.makePersistent(todo);
			pm.makePersistent(user);
		} catch (Exception e) {
			user = new ranveer.User(user_key, u.getEmail());
			pm.makePersistent(user);
			pm.close();
			pm = PMF.get().getPersistenceManager();
			user = pm.getObjectById(ranveer.User.class, user_key);
			todo.setParent(user);
			user.addTodo(todo);
			pm.makePersistent(todo);
			pm.makePersistent(user);
		} finally {
			pm.close();
			displayAlert("Todo Added !", "'/'", out);
		}
	}

}

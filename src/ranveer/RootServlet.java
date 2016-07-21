package ranveer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class RootServlet extends HttpServlet {

	ArrayList<String> todos=new ArrayList<>();
	ArrayList<Boolean> check_list=new ArrayList<>();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		String login_url = us.createLoginURL("/");
		String logout_url = us.createLogoutURL("/");
		
		req.setAttribute("user", u);
		req.setAttribute("login_url", login_url);
		req.setAttribute("logout_url", logout_url);
		req.setAttribute("addLink", "/add");
		
		String user_id;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			if (u != null) {
				user_id = u.getUserId();
				Key user_key = KeyFactory.createKey("User", user_id);
				ranveer.User user;
				user = pm.getObjectById(ranveer.User.class, user_key);
				
				check_list.clear();todos.clear();
				for (Todo ctemp : user.getTodos()) {
					todos.add(ctemp.getName().toUpperCase() + " ( " + ctemp.getDate() +" ) "+"     <===>    ");
					check_list.add(ctemp.isChecked());
				}
				req.setAttribute("to-do-list",todos);
				req.setAttribute("status",check_list);
			}
		}catch(Exception e){
			user_id = u.getUserId();
			Key user_key = KeyFactory.createKey("User", user_id);
			ranveer.User user = new ranveer.User(user_key,u.getEmail());
			pm.makePersistent(user);
			req.setAttribute("to-do-list", null);
			req.setAttribute("status", null);
		}
		finally{pm.close();}
		
		//dispatch
		RequestDispatcher req_dispatcher = req.getRequestDispatcher("/WEB-INF/root.jsp");
		req_dispatcher.forward(req, resp);
	}
}
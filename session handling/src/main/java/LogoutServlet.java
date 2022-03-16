import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		out.write("<html><body>");
		HttpSession session = req.getSession();
		session.invalidate();
		out.write("You're logged out");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
		rd.include(req, res);
	}
}

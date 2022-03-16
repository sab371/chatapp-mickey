import java.io.IOException;
import dataStore.DataCheck;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//@WebServlet("/home")

public class LogServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res ) throws IOException, ServletException {
		String usrname = req.getParameter("username");
		String password = req.getParameter("password");
		PrintWriter out = res.getWriter();
		DataCheck dc = new DataCheck();
		try {
			if(dc.check(usrname,password)) {
				HttpSession session = req.getSession();
				session.setAttribute("username", usrname);
				session.setAttribute("password", password);
				session.setMaxInactiveInterval(5*60);
				
			}
			else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
				out.write("<b>Invalid login!!! Please try again</b>");
				rd.include(req,res);
				//out.close();
			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

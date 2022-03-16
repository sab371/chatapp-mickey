import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;

public class SignUpServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException, ServletException{
		
		PrintWriter out = res.getWriter();
		String usrname = req.getParameter("username");
		Criteria c = new Criteria(new Column("UserAuthentication", "USER_NAME"),usrname, QueryConstants.EQUAL);
		DataObject d = null;
		try {
			d = DataAccess.get("UserAuthentication",c);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator it = null;
		try {
			it = d.getRows("UserAuthentication");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(it.hasNext())
		{
			out.println("<b>username already exists!!! Create a new one</b>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signUp.html");
			rd.include(req, res);
		}
		else {
			String password = req.getParameter("password");
			
			
			Row r = new Row ("UserAuthentication");
			r.set("USER_NAME", usrname);
			r.set("PASSWORD", password);

			
			DataObject d1=new WritableDataObject();
			try {
				d1.addRow(r);
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				DataAccess.add(d1);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<b>You're Successfully signed in!!!</b><br>"
					+ "<a href=\"/session handling/index.html\">Go back to login page</a>");
		}
		
	}
}

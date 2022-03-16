package dataStore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;

public class RequestSender extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String name = (String)session.getAttribute("username");
		String path = req.getRequestURI();
		String segments[] = path.split("/");
		String frndname = segments[segments.length-1];
		Row r = new Row ("FriendsList");
		r.set("USER_NAME", name);
		r.set("FRIEND", frndname);
		r.set("STATUS", "Requested");
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
		PrintWriter out = res.getWriter();
		out.write("Request Sent");
	}
}

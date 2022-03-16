package dataStore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.UpdateQuery;
import com.adventnet.ds.query.UpdateQueryImpl;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;

public class RequestAccept extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String name = (String)session.getAttribute("username");
		String path = req.getRequestURI();
		String segments[] = path.split("/");
		String frndname = segments[segments.length-1];
		Criteria c = (new Criteria(Column.getColumn("FriendsList", "USER_NAME"), frndname, QueryConstants.EQUAL)).and(new Criteria(Column.getColumn("FriendsList", "FRIEND"), name, QueryConstants.EQUAL)); 		
		
		UpdateQuery uq = new UpdateQueryImpl("FriendsList");
		uq.setUpdateColumn("STATUS","Accepted");
		
		uq.setCriteria(c);
		Row r = new Row ("FriendsList");
		r.set("USER_NAME", name);
		r.set("FRIEND", frndname);
		r.set("STATUS", "Accepted");
		DataObject d1=new WritableDataObject();
		try {
			DataAccess.update(uq);
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
		out.write("Request Accepted");
	}
}

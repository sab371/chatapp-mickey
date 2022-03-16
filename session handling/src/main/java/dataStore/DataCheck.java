package dataStore;

import java.util.Iterator;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;

public class DataCheck {
	public boolean check(String uname,String pass) {
		Criteria c = new Criteria(new Column("UserAuthentication", "USER_NAME"),uname, QueryConstants.EQUAL);
		Criteria c1 = c.and(new Column("UserAuthentication", "PASSWORD"),pass,QueryConstants.EQUAL);
		DataObject d = null;
		try {
			d = DataAccess.get("UserAuthentication",c1);
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
			return true;
			
		}
		else {
			return false;
		}
		
	}

}

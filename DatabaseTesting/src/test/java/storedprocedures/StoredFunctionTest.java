package storedprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoredFunctionTest {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs;
	ResultSet rs1;
	ResultSet rs2;
	CallableStatement cstmt;
	
	@BeforeClass
	public void setup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
		
	}
	
	@Test(priority = 1)
	public void test_storedFunctionExists() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("show function status where name ='customerlevel'");
		rs.next();
		Assert.assertEquals(rs.getString("Name"), "customerlevel");
	}
	@Test(priority = 2)
	public void test_CustomerLevel_with_sql_statement() throws SQLException {
		stmt = con.createStatement();
		rs1 = stmt.executeQuery("select customerName,customerlevel(creditlimit) from customers");	
		stmt = con.createStatement();
		rs2 = stmt.executeQuery("select customerName,\r\n"
				+ "case \r\n"
				+ "	when creditlimit > 50000 then 'platinum'\r\n"
				+ "    when creditlimit >=10000 and creditlimit < 50000 then 'gold'\r\n"
				+ "    when creditlimit < 10000 then 'silver'\r\n"
				+ " end as customerlevel from customers");
		Assert.assertEquals(compareResultSet(rs1, rs2), true);
		
		
	}
	
	
	@Test(priority = 2)
	public void test_CustomerLevel_with_procedure() throws SQLException {
		
		cstmt=con.prepareCall("{call GetCustomerLevel(?,?)}");
		cstmt.setInt(1,131);
		cstmt.registerOutParameter(2, Types.VARCHAR);
		
		cstmt.executeQuery();	
		
		String cust_level=cstmt.getString(2);
		
		stmt = con.createStatement();
		rs = stmt.executeQuery("select customerName,\r\n"
				+ "case \r\n"
				+ "	when creditlimit > 50000 then 'platinum'\r\n"
				+ "    when creditlimit >=10000 and creditlimit < 50000 then 'gold'\r\n"
				+ "    when creditlimit < 10000 then 'silver'\r\n"
				+ " end as customerlevel from customers where customerNumber=131");
		rs.next();
		String exp_custlevel=rs.getString("customerlevel");
		Assert.assertEquals(cust_level,exp_custlevel);
		
		
	}
	
	
	public boolean compareResultSet(ResultSet rs1, ResultSet rs2) throws SQLException {
		while (rs1.next()) {
			rs2.next();
			int count = rs1.getMetaData().getColumnCount();
			for (int i = 1; i <= count; i++) {
				if (!StringUtils.equals(rs1.getString(i), rs2.getString(i))) {
					return false;
				}
			}
		}
		return true;
	}


}

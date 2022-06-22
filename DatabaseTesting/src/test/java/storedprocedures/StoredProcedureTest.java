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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoredProcedureTest {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs;
	CallableStatement cstmt;
	ResultSet rs1;
	ResultSet rs2;

	@BeforeClass
	public void setup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
	}

	/*
	 * syntax for calling stored procedures {call procedurename()} {call
	 * procedurename(?)} {?=call procedurename()} {?=call procedurename(?,?)}
	 * 
	 */
	@Test(priority = 1)
	public void test_storedProceduresExists() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("show procedure status where name ='selectallcust'");
		rs.next();
		Assert.assertEquals(rs.getString("Name"), "selectallcust");
	}

	@Test(priority = 2)
	public void test_SelectAllCustomers() throws SQLException {

		cstmt = con.prepareCall("{call selectallcust()}");
		rs1 = cstmt.executeQuery();

		stmt = con.createStatement();
		rs2 = stmt.executeQuery("select * from customers");

		Assert.assertEquals(compareResultSet(rs1, rs2), true);

	}

	@Test(priority = 3)
	public void test_SelectAllCustomersByCity() throws SQLException {

		cstmt = con.prepareCall("{call SelectAllCustomersByCity(?)}");
		cstmt.setString(1, "singapore");
		rs1 = cstmt.executeQuery();

		stmt = con.createStatement();
		rs2 = stmt.executeQuery("select * from customers where city='singapore'");

		Assert.assertEquals(compareResultSet(rs1, rs2), true);

	}

	@Test(priority = 4)
	public void test_getOrderByCust() throws SQLException {

		cstmt = con.prepareCall("{call get_order_by_cust(?,?,?,?,?)}");
		cstmt.setInt(1, 141);

		cstmt.registerOutParameter(2, Types.INTEGER);
		cstmt.registerOutParameter(3, Types.INTEGER);
		cstmt.registerOutParameter(4, Types.INTEGER);
		cstmt.registerOutParameter(5, Types.INTEGER);

		cstmt.executeQuery();

		int shipped = cstmt.getInt(2);
		int cancelled = cstmt.getInt(3);
		int resolved = cstmt.getInt(4);
		int disputed = cstmt.getInt(5);
		stmt = con.createStatement();
		rs = stmt.executeQuery(
				"select (SELECT count(*) as 'shipped' From orders where customerNumber=141 and status='Shipped') as shipped,(SELECT count(*) as 'cancelled' From orders where customerNumber=141 and status='cancelled') as cancelled,(SELECT count(*) as 'resolved' From orders where customerNumber=141 and status='resolved') as resolved,(SELECT count(*) as 'disputed' From orders where customerNumber=141 and status='disputed') as disputed");
		rs.next();
		int exp_shipped = rs.getInt("shipped");
		int exp_cancelled = rs.getInt("cancelled");
		int exp_resolved = rs.getInt("resolved");
		int exp_disputed = rs.getInt("disputed");

		if (shipped == exp_shipped && cancelled == exp_cancelled && resolved == exp_resolved
				&& disputed == exp_disputed)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}

	@Test(priority = 5)
	public void test_getCustomerShipping() throws SQLException {

		cstmt = con.prepareCall("{call GetCustomerShipping(?,?)}");
		cstmt.setInt(1, 112);
		cstmt.registerOutParameter(2, Types.VARCHAR);
		cstmt.executeQuery();

		String shipping_time = cstmt.getString(2);
		stmt = con.createStatement();
		rs = stmt.executeQuery(
				"Select country , Case When country='USA' Then '2-day shipping' When country='Canada' Then ' 3-day shipping'  ELSE '5- day shipping' End as ShippingTime  From Customers where customerNumber=112");
		rs.next();
		String exp_shippingtime = rs.getString("ShippingTime");

		if (shipping_time.equals(exp_shippingtime))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
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

	@AfterClass
	public void tearDown() throws SQLException {
		con.close();
	}

}
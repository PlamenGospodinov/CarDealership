import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class DBSaleHelper {
	
	static Connection conn = null;
	static PreparedStatement state = null;
	static SaleModel model = null;
	static ResultSet result = null;
	
	static SaleModel getAllData() {
		conn = getConnection();
		String sql = "SELECT S.SALEID,B.BRAND,"
				+ "C.MODEL,S.SALEDATE,S.FIRSTNAME,"
				+ "S.LASTNAME,S.SALEPRICE,"
				+ "S.DIFFERENCE "
				+ "FROM SALES S JOIN CARS C "
				+ "ON S.CARID = C.CARID "
				+ "JOIN BRANDS B "
				+ "ON C.BRANDID = B.ID";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new SaleModel(result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	
	static SaleModel getSearchData(String firstName) {
		conn = getConnection();
		String sql = "SELECT S.SALEID,B.BRAND,"
				+ "C.MODEL,S.SALEDATE,S.FIRSTNAME,"
				+ "S.LASTNAME,S.SALEPRICE,"
				+ "S.DIFFERENCE "
				+ "FROM SALES S JOIN CARS C "
				+ "ON S.CARID = C.CARID "
				+ "JOIN BRANDS B "
				+ "ON C.BRANDID = B.ID WHERE FIRSTNAME = \'" + firstName + "\'";
		
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new SaleModel(result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	
	static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			File file = new File("C:\\Users\\Plamen\\git\\CarDealership\\CarDealership\\src\\config.txt");
			Scanner sc = new Scanner(file);
			String connString = "",username = "",password = "";
			while(sc.hasNextLine()) {
				connString = sc.nextLine().trim();
				username = sc.nextLine().trim();
				password = sc.nextLine().trim();
			}
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(connString, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
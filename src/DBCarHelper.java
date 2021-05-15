import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class DBCarHelper {
	
	static Connection conn = null;
	static PreparedStatement state = null;
	static MyModel model = null;
	static ResultSet result = null;
	
	//static ArrayList<String> firstCol = new ArrayList<String>();
	
	static MyModel getAllData() {
		conn = getConnection();
		String sql = "SELECT C.CARID,B.BRAND,C.MODEL,C.YEAR,C.PRICE,C.COMMENT\r\n"
				+ "FROM CARS C JOIN BRANDS B\r\n"
				+ "WHERE C.BRANDID = B.ID\r\n"
				+ "ORDER BY C.MODEL";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	
	static int getBrandData(String brand) {
		conn = getConnection();
		String sql = "SELECT * "
				+ "FROM BRANDS "
				+ "WHERE BRAND = \'" + brand + "\'";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(model.getValueAt(0, 0).toString());
	}
	
	//------SEARCH DATA AFTER CLICKING SEARCH BUTTON
	static MyModel getSearchData(String price) {
		conn = getConnection();
		String sql = "SELECT C.CARID,B.BRAND,C.MODEL,C.YEAR,C.PRICE,C.COMMENT\r\n"
				+ "FROM CARS C JOIN BRANDS B\r\n"
				+ "ON C.BRANDID = B.ID\r\n"
				+  " WHERE PRICE > " + Integer.parseInt(price)
					+ " ORDER BY C.MODEL";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
			
			
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
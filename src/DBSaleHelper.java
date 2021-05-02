import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBSaleHelper {
	
	static Connection conn = null;
	static PreparedStatement state = null;
	static SaleModel model = null;
	static ResultSet result = null;
	
	static SaleModel getAllData() {
		conn = getConnection();
		String sql = "SELECT*FROM SALES";
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
		String sql = "SELECT * FROM SALES WHERE FIRSTNAME = \'" + firstName + "\'";
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
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Plamen\\eclipse-workspace", "sa", "sa");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
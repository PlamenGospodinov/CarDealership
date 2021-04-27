import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBCarHelper {
	
	static Connection conn = null;
	static PreparedStatement state = null;
	static MyModel model = null;
	static ResultSet result = null;
	
	//static ArrayList<String> firstCol = new ArrayList<String>();
	
	static MyModel getAllData() {
		conn = getConnection();
		String sql = "SELECT*FROM CARS";
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
	
	static MyModel getSearchData(String price) {
		conn = getConnection();
		String sql = "SELECT*FROM CARS WHERE PRICE > " + Integer.parseInt(price);
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
	
	
	//get Brands
	/*static ArrayList<String> getBrandData() {
		conn = getConnection();
		String sql = "SELECT BRAND FROM BRANDS";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
			firstCol = model.getBrands();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstCol;
	}
	*/
	
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
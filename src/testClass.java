import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testClass {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
			File file = new File("C:\\Users\\Plamen\\git\\CarDealership\\CarDealership\\src\\config.txt");
			Scanner sc = new Scanner(file);
			String connString = "",username = "",password = "";
			while(sc.hasNextLine()) {
				connString = sc.nextLine().trim();
				username = sc.nextLine().trim();
				password = sc.nextLine().trim();
			}
			
			System.out.println(connString);
			System.out.println(username);
			System.out.println(password);
	}

}

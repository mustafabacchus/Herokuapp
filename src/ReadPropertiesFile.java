import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	public static Properties prop;
	
	public static void main(String[] args) {
		prop = new Properties();
		
		try {
			FileInputStream fin = new FileInputStream(System.getProperty("user.dir") +
					"\\src\\config\\credentials.properties");
			prop.load(fin);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
	}

}

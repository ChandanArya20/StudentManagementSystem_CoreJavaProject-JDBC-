package in.ineuron.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class jdbcUtils {
	
	private jdbcUtils(){
		
	}
	
	public static Connection getDBconnection() throws FileNotFoundException, IOException, SQLException {
		
		Properties properties=new Properties();
		properties.load(new FileInputStream("src\\in\\ineuron\\properties\\application.properties"));
		
		Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user")
								,properties.getProperty("password"));	
		
		return connection;
	}
	
	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		
		if(resultSet!=null) {
			resultSet.close();
		}
		if(statement!=null) {
			statement.close();
		}
		if(connection!=null) {
			connection.close();
		}
	}

}

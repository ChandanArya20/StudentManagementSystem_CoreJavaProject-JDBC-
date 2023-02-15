package in.ineuron.persistence;

import in.ineuron.dto.Student;

import in.ineuron.utils.jdbcUtils;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {

	//Resoures used
	Connection connection=null;
	PreparedStatement pstm=null;
	Scanner scanner=null;
	
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		
		String query ="insert into studentrecord(sname,sage,saddress)values(?,?,?)";
			
		try {
			
			
			//establish the connection to the database
			connection=jdbcUtils.getDBconnection();
			
			if(connection!=null)
				pstm=connection.prepareStatement(query);
		 
			if(pstm!=null) {
				
				scanner=new Scanner(System.in);
		
				//set input values to query
				pstm.setString(1, sname);
				pstm.setInt(2, sage);
				pstm.setString(3, saddress);
			
				int rowCount=pstm.executeUpdate();
				
				if(rowCount==1)
					return "success";
			}
			
		} catch (IOException | SQLException  e) {	
			return "failure";
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		
		String query="select * from studentrecord where sid =?";
		ResultSet resultSet=null;
		Student stud=null;	
		
		try {
			connection=jdbcUtils.getDBconnection();
			
			if(connection!=null)
				pstm=connection.prepareStatement(query);
			
			if(pstm!=null) {
				pstm.setInt(1, sid);
				resultSet=pstm.executeQuery();
			}
			
			if(resultSet.next()) {		
				stud=new Student();
				stud.setSid(resultSet.getInt(1));
				stud.setSname(resultSet.getString(2));
				stud.setSage(resultSet.getInt(3));
				stud.setSaddress(resultSet.getString(4));
				
				return stud;
			}
			
		} catch (IOException | SQLException e) {
			return stud;
		}
		return stud;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		
		//Resoures used
		Connection connection=null;
		PreparedStatement pstm=null;
		Scanner scanner=null;
		
		String query ="update studentrecord set sname=?, sage=?, saddress=? where sid=?";		
		try {		
			connection=jdbcUtils.getDBconnection();			
			if(connection!=null)
				pstm=connection.prepareStatement(query);	
			
			if(pstm!=null) {						
				pstm.setString(1, sname);
				pstm.setInt(2, sage);
				pstm.setString(3, saddress);
				pstm.setInt(4, sid);
				
				int rowCount=pstm.executeUpdate();
				
				if(rowCount==1)
					return "success";
				else
					return "not found";
			}
			
		} catch (IOException | SQLException e) {	
			return "failure";
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		
		String query ="delete from studentrecord where sid=?";	
		try {		
			//establish the connection to the database
			connection=jdbcUtils.getDBconnection();
			
			if(connection!=null)
				pstm=connection.prepareStatement(query);
		 
			if(pstm!=null) {
				
				pstm.setInt(1, sid);
				int rowCount=pstm.executeUpdate();
				
				if(rowCount==1)
					return "success";
				else
					return "not found";
			}
			
		} catch (IOException | SQLException e) {	
			return "failure";
		}
		return "failure";
	}
}

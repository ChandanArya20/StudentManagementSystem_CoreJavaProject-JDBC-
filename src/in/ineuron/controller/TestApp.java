package in.ineuron.controller;

import java.io.IOException;
import java.util.Scanner;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

//Runtime.getRuntime().exec("cmd /c cls");

//controller logic
public class TestApp {
	
	static Scanner scanner=new Scanner(System.in);
	static IStudentService studentService=StudentServiceFactory.getStudentService();
	
	public static void main(String[] args) throws IOException {

		while(true) {			
			System.out.println("\n1. Add Record");
			System.out.println("2. Search Record");
			System.out.println("3. Delete Record");
			System.out.println("4. Update Record");
			System.out.println("5. Exit");
			System.out.print("Enter your choice [1-5]: ");
	        int choice = scanner.nextInt();
			
			switch(choice) {			
				case 1 : insertStudent();
						 break;
				case 2 : searchStudent();
						 break;
				case 3 : deleteStudent();
						 break;
				case 4 : updateStudent();
					     break;
				case 5 : System.out.println("Thanks for using our application...");
						 System.exit(0);
				
				default : System.out.println("You entered wronng choice, Please enter between 1 to 5");			
			}			
			
		}
	}

	private static void insertStudent() {
	
		String name=null;
		Integer age=null;
		String address=null;
		
		System.out.print("Enter Name    : ");
		name=scanner.next();
		System.out.print("Enter Age     : ");
		age=scanner.nextInt();
		System.out.print("Enter Address : ");
		address=scanner.next();
		
		String msg = studentService.addStudent(name, age, address);
		
		if (msg.equalsIgnoreCase("success"))			
			System.out.println("record inserted succesfully");
		else
			System.out.println("record insertion failed....");
	}
	
	private static void searchStudent() {
		
		System.out.print("Enter the student_id : ");
		int sid=scanner.nextInt();
		Student std=studentService.searchStudent(sid);
		if(std!=null) {
			System.out.println("\nSid\tName\tAge\tAddress");
			System.out.println(std.getSid()+"\t"+std.getSname()+"\t"+std.getSid()+"\t"+std.getSname());
		}else {
			System.out.println("Record not found for given id "+sid);
		}
	}
	
	private static void deleteStudent() {
		
		System.out.print("Enter the student_id : ");
		int sid=scanner.nextInt();
		
		String status=studentService.deleteStudent(sid);
		if(status.equals("success"))
			System.out.println("Record deleted successfully...");
		else if(status.equals("not found"))
			System.out.println("Record not available for given id : "+sid);
		else
			System.out.println("Record Deletion failed....");	
	}
	private static void updateStudent() {
		
		System.out.println("Enter the student_id : ");
		int id=scanner.nextInt();
		Student stud=studentService.searchStudent(id);
		if(stud!=null) {
			
			System.out.println("Stuent id is "+stud.getSid());
			System.out.println("Old name is "+stud.getSname()+" ,Enter new name : ");
			String name=scanner.next();
			System.out.println("Old age is "+stud.getSage()+" ,Enter new age : ");
			Integer age=scanner.nextInt();
			System.out.println("Old address is "+stud.getSaddress()+" ,Enter new address : ");
			String address=scanner.next();
			
			String status=studentService.updateStudent(id, name, age, address);
			
			if(status.equals("success")) 
				System.out.println("Record updated successfully...");
			else if(status.equals("not found"))
				System.out.println("Record not found...");
			else
				System.out.println("something went wrong..., Record updation failed... ");
		}
	}

}

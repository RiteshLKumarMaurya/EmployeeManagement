package com.lilijini.yp;

import java.sql.ResultSet;

import com.lilijini.auth.AuthMain;
import com.lilijini.auth.Input;
import com.lilijini.yp.dao.EmployeeDao;
import com.lilijini.yp.dao.InvalidChoiceException;

public class YourParcel {

	static EmployeeDao emp;
	static {
		AuthMain.main(null);
		emp = new EmployeeDao();
	}

	public static void main(String[] args) {
		
		try{
			Thread.sleep(300);
			for(int i=0;i<22;i++) {
				System.out.print("\u2728 - ");
				
			}
			System.out.println();
		}catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		int choice = Input.readInt("Enter your choice\n1. Add Employee\n2.Find Employee By name\n3.Find Employee salary b/w\n4. UpdateEmpSalaryByJob\n5. Delete Emp by Id\nchoice");

		switch (choice) {
		case 1:
			emp.addEmployee();
			break;
		case 2:
			ResultSet rs = emp.findEmployeeByName(Input.readString("Enter empName to find"));
			// process the result
			try {
				processresult(rs);
			} catch (Exception e) {
				System.out.println("1: " + e.getLocalizedMessage());
			}

			break;
		case 3:
			ResultSet rs2 = emp.findEmployeeSalaryBetween(Input.readDouble("Enter range low salary"),
					Input.readDouble("Enter range high salary"));
			// need to process the results
			try {
				processresult(rs2);
			} catch (Exception e) {
				System.out.println("2: " + e.getLocalizedMessage());
			}

			break;

		case 4:
			emp.updateEmployeeSalaryByDesignation(Input.readString("Enter job"));

			break;

		case 5:
			emp.deleteEmployeeById(Input.readInt("Enter empId to delete record"));
			break;

		default:
			try {
				throw new InvalidChoiceException("invalid choice not allow here!");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

	}

	public static void processresult(ResultSet rs) throws Exception {
		if (rs != null) {
			int i = 0;
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<:All emp details:>>>>>>>>>>>>>>>>>>>>>>>>>>");
			while (rs.next()) {
				System.out.println("Employees Detail: " + i);
				System.out.println("empId: " + rs.getInt(1));
				System.out.println("empName: " + rs.getString(2));
				System.out.println("empEmail: " + rs.getString(3));
				System.out.println("empJob: " + rs.getString(4));
				System.out.println("empSalary: " + rs.getDouble(5));
				i++;
				System.out.println();
			}
		} else {
			System.out.println("throw an exeption that rs is null!");
		}
	}

}

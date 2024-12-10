package com.lilijini.yp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lilijini.auth.Input;
import com.lilijini.yp.EmpOperations;

public class EmployeeDao implements EmpOperations {
	public static Connection conn;
	PreparedStatement pst;
	String query;

	public EmployeeDao() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yp?user=root&password=ghost$fuck$love");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void addEmployee() {

		System.out.println("------------------------ Adding the emp --------------------------");
		query = "insert into emp values(?,?,?,?,?)";
		try {

			pst = conn.prepareStatement(query);
			System.out.println("<<<<<<<<<<<<<<<<<<< Enter all employee details >>>>>>>>>>>>>>>>>>>");
			pst.setInt(1, Input.readInt("Enter id"));
			pst.setString(2, Input.readString("Enter Name"));
			pst.setString(3, Input.readString("Enter Email"));
			pst.setString(4, Input.readString("Enter job"));
			pst.setDouble(5, Input.readDouble("Enter salary"));
			int rows = pst.executeUpdate();
			System.out.println(rows + " rows Inserted!");
		} catch (Exception e) {
			System.out.println("Error in inserting: " + e.getLocalizedMessage() + e.getStackTrace());
		}
	}

	@Override
	public ResultSet findEmployeeByName(String name) {
		System.out.println("------------------------ Finding the emp by name --------------------------");

		ResultSet rs = null;
		try {
			query = "SELECT * FROM EMP WHERE empName='" + name + "'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Exception in fetching record by name: " + e.getLocalizedMessage());
		}
		return rs;
	}

	@Override
	public ResultSet findEmployeeSalaryBetween(double low, double high) {
		System.out.println("------------------------ Finding the emp by sal range --------------------------");

		ResultSet rs = null;
		try {
			query = "SELECT * FROM EMP WHERE empSalary between " + low + " and " + high;

			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Exception in fetching record by sal range: " + e.getLocalizedMessage());
		}
		return rs;

	}

	@Override
	public void updateEmployeeSalaryByDesignation(String job) {
		System.out.println("------------------------ Updating the emp salary by job--------------------------");

		try {
//			double salary = getSalaryByDesignation(job);
//			System.out.println(salary = salary + 50000);
			query = "UPDATE EMP SET empSalary=empSalary +" + 5000 + " where empJob='" + job + "'";
			pst = conn.prepareStatement(query);
			var x = pst.executeUpdate();
			if (x > 0)
				System.out.println(x + " rows updated!");
			else
				System.out.println("updation failed!");
		} catch (Exception e) {
			System.out.println("at updating salary by job: " + e.getLocalizedMessage());
		}

	}

	@Override
	public void deleteEmployeeById(int id) {
		System.out.println("------------------------ deleting the emp by id --------------------------");

		try {

			query = "delete from emp where empId=" + id;
			pst = conn.prepareStatement(query);
			int x = pst.executeUpdate();
			if (x > 0) {
				System.out.println(x + " rows deleted!");
			} else {
				System.out.println("no rows deleted!");
			}

		} catch (Exception e) {
			System.out.println("exception at deleting emp: " + e.getLocalizedMessage());
		}
	}

	public double getSalaryByDesignation(String job) {
		double sal = 0.0;
		try {
			query = "select empSalary from emp where empJob='" + job + "'" + " limit 1";
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			sal = rs.getDouble("empSalary");
			System.out.println("salary: " + sal);
		} catch (Exception e) {
			System.out.println("exception in getting salary: " + e.getLocalizedMessage());
		}
		return sal;
	}
}
package com.lilijini.yp;

import java.sql.ResultSet;

public interface EmpOperations {
	public void addEmployee();

	public ResultSet findEmployeeByName(String name);

	public ResultSet findEmployeeSalaryBetween(double low, double high);

	public void updateEmployeeSalaryByDesignation(String job);// 50,000

	public void deleteEmployeeById(int id);
}

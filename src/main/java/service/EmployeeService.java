package service;

import java.util.List;

import pojos.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee);
	
	public Employee readEmployee(int employeeId);
	
	public List<Employee> readAllEmployees();
	
	public Employee updateEmployee(int employeeId, Employee employee);
	
	public void deleteEmployee(int employeeId);

}

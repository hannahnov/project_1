package daos;

import java.util.List;

import pojos.Employee;

public interface EmployeeDao {
	
	public void createEmployee(Employee employee);
	
	public Employee readEmployee(int employeeId);
	
	public List<Employee> readAllEmployees();
	
	public Employee updateEmployee(int employeeId, Employee employee);
	
	public int deleteEmployee(int employeeId);

}

package service;

import java.util.List;

import org.apache.log4j.Logger;
import daos.EmployeeDao;
import daos.EmployeeDaoPostgres;
import pojos.Employee;

public class EmployeeServiceFullStack implements EmployeeService {
	
	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	private static java.util.logging.Logger log = Logger.getRootLogger();

	@Override
	public Employee createEmployee(Employee employee) {
		log.info("Employee service: create employee");
		
		employeeDao.createEmoloyee(employee);
		
		return employee;
	}

	@Override
	public Employee readEmployee(int employeeId) {
		log.info("Employee service: read employee");
		
		return employeeDao.readEmployee(employeeId);
	}

	@Override
	public List<Employee> readALlEmployees() {
		log.info("Employee service: read all employees");
		
		return employeeDao.readAllEmployees();
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		log.info("Employee service: udpate employee");
		
		employeeDao.updateEmployee(employeeId, employee);
		
		return employee;
	}

	@Override
	public void deleteEmployee(int employeeId) {
		log.info("Employee service: delete employee");
		
		employeeDao.deleteEmployee(employeeId);
		
	}
	


}

package daos;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Employee;
import pojos.EmployeeRank;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventService;
import service.EventServiceFullStack;

public class EmployeeDaoPostgres implements EmployeeDao {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	EventService eventService = new EventServiceFullStack();
	
	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement statement;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	

	@Override
	public void createEmployee(Employee employee) {
		
		log.info("Employee dao postgres: creating employee");
		
		String sql = "insert into employees (employee_id, first_name, last_name, directsupervisor_id, "
				+ "available_reimbursement, pending_Reimbursement, employee_rank, email_address, department_id, awarded_reimbursement)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, employee.getEmplId());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setInt(4, employee.getDirectSupervisorId());
			statement.setDouble(5, employee.getAvailableReimbursement());
			statement.setDouble(6, employee.getPendingReimbursement());
			statement.setInt(7, employee.getEmployeeRank().getValue());
			statement.setString(8, employee.getEmailAddress());
			statement.setInt(9, employee.getDepartmentId());
			statement.setDouble(10, employee.getAwardedReimbursement());
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public Employee readEmployee(int employeeId) {
		log.info("employeeDaoPostgres: reading employee");
		String sql = "select * from employees where employee_id = ?";
		Employee employee = new Employee();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, employeeId);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				String emailAddress = rs.getString("email_address");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int supervisorId = rs.getInt("directsupervisor_id");
				double availableReimbursement = rs.getDouble("available_reimbursement");
				double pendingReimbursement = rs.getDouble("pending_reimbursement");
				EmployeeRank employeeRank = EmployeeRank.valueOf(rs.getInt("employee_rank"));
				int departmentId = rs.getInt("department_id");
				double awardedReimbursement = rs.getDouble("awarded_reimbursement");
				employee = new Employee(emailAddress, departmentId, employeeId, firstName, 
						lastName, supervisorId, availableReimbursement, pendingReimbursement, employeeRank, awardedReimbursement);
		
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> readAllEmployees() {
		log.info("Employee dao postgres reading all Employees");
		List<Employee> employeeList = new ArrayList<>();
		
		String sql = "select * from employees";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String emailAddress = rs.getString("email_address");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int supervisorId = rs.getInt("directsupervisor_id");
				double availableReimbursement = rs.getDouble("available_reimbursement");
				double pendingReimbursement = rs.getDouble("pending_reimbursement");
				EmployeeRank employeeRank = EmployeeRank.valueOf(rs.getInt("employee_rank"));
				int departmentId = rs.getInt("department_id");
				double awardedReimbursement = rs.getDouble("awarded_reimbursement");
				Employee employee = new Employee(emailAddress, departmentId, employeeId, firstName, 
						lastName, supervisorId, availableReimbursement, pendingReimbursement, employeeRank, awardedReimbursement);
				employeeList.add(employee);
			} 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employeeList;
		}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		log.info("Employee dao postgres: updating employee");
		String sql = "update employees set first_name = ?, last_name = ?, supervisor_Id = ?"
				+ " available_reimbursement = ?, pending_reimbursement = ?, employee_rank = ?, email_address = ?"
				+ ", deparment_id = ?, awarded_reimbursement = ? "
				+ " where employeeid = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setInt(3, employee.getDirectSupervisorId());
			statement.setDouble(4, employee.getAvailableReimbursement());
			statement.setDouble(5, employee.getPendingReimbursement());
			statement.setInt(6, employee.getEmployeeRank().getValue());
			statement.setString(7, employee.getEmailAddress());
			statement.setInt(8, employee.getDepartmentId());
			statement.setDouble(9, employee.getAwardedReimbursement());
			statement.setInt(10, employeeId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int deleteEmployee(int employeeId) {
		log.info("employee dao postgres: deleting employee");
		int rowsToDelete = 0;
		String sql = "delete from employees where employee_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
		statement = conn.prepareStatement(sql);	
		statement.setInt(1, employeeId);
		rowsToDelete = statement.executeUpdate();
		
		if (rowsToDelete == 0) {
		System.out.println("No rows to delete");
		
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return rowsToDelete;
	}
}

package daos;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Approver;
import pojos.Employee;
import pojos.EmployeeRank;
import pojos.Event;
import pojos.Requestor;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventService;
import service.EventServiceFullStack;

public class EmployeeDaoPostgres implements EmployeeDao {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	EventService eventService = new EventServiceFullStack();
	
	private static java.util.logging.Logger log = Logger.getRootLogger();
	
	private PreparedStatement statement;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	

	@Override
	public void createEmployee(Employee employee) {
		
		log.info("Employee dao postgres: creating employee");
		
		String sql = "insert into employees (employeeid, firstname, lastname, password, username, directsupervisorid, "
				+ "availablereimbursement, pendingReimbursement, employeerank, isrequesting, eventid)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, employee.getEmplId());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getPassword());
			statement.setString(5, employee.getUserName());
			statement.setInt(6, employee.getDirectSupervisorId());
			statement.setDouble(7, employee.getAvailableReimbursement());
			statement.setDouble(8, employee.getPendingReimbursement());
			statement.setInt(9, employee.getEmployeeRank().getValue());
			statement.setBoolean(10, employee.isRequesting());
			statement.setInt(11, employee.getEvent().getEventId());
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public Employee readEmployee(int employeeId) {
		log.info("employeeDaoPostgres: reading employee");
		String sql = "select * from employees where employeeid = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, employeeId);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				String userName = rs.getString("username");
				int supervisorId = rs.getInt("directsupervisorId");
				double availableReimbursement = rs.getDouble("availablereimbursement");
				double pendingReimbursement = rs.getDouble("pendingreimbursement");
				EmployeeRank employeeRank = EmployeeRank.valueOf(rs.getInt("employeerank"));
				Boolean isRequesting = rs.getBoolean("isrequesting");
				Boolean isApproving = !isRequesting;
				int eventId = rs.getInt("eventid");
				Event event;
				if (isRequesting) {
			event = eventService.readEvent(eventId);
			Requestor empl = (Requestor) new Employee(employeeId, firstName, lastName, userName, password, supervisorId,
							event, availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);	
			employeeService.createEmployee(empl);
							return empl;
							}
			if (isApproving) {
			Requestor empl = (Requestor) new Employee(employeeId, firstName, lastName, userName, password, supervisorId, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
					employeeService.createEmployee(empl);
					return empl;
						}
		
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
				int employeeId = rs.getInt("employeeid");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				String userName = rs.getString("username");
				int supervisorId = rs.getInt("directsupervisorId");
				double availableReimbursement = rs.getDouble("availablereimbursement");
				double pendingReimbursement = rs.getDouble("pendingreimbursement");
				EmployeeRank employeeRank = EmployeeRank.valueOf(rs.getInt("employeerank"));
				Boolean isRequesting = rs.getBoolean("isrequesting");
				Boolean isApproving = !isRequesting;
				int eventId = rs.getInt("eventid");
				Event event;
				if (isRequesting) {
			event = eventService.readEvent(eventId);
			Requestor empl = (Requestor) new Employee(employeeId, firstName, lastName, userName, password, supervisorId,
							event, availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);	
			employeeService.createEmployee(empl);
						employeeList.add(empl);
							}
			if (isApproving) {
			Requestor empl = (Requestor) new Employee(employeeId, firstName, lastName, userName, password, supervisorId, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
					employeeService.createEmployee(empl);
					employeeList.add(empl);
						}
		
			} 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employeeList;
		}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		log.info("Employee dao postgres: updating employee");
		String sql = "update employees set firstname = ?, lastname = ?, password = ?, supervisorId = ?, eventid = ?,"
				+ " availablereimbursement = ?, pendingreimbursement = ?, employeerank = ?, isrequesting = ?, isapproving = ?"
				+ " where employeeid = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getPassword());
			statement.setString(4, employee.getUserName());
			statement.setInt(5, employee.getDirectSupervisorId());
			statement.setDouble(6, employee.getAvailableReimbursement());
			statement.setDouble(7, employee.getPendingReimbursement());
			statement.setInt(8, employee.getEmployeeRank().getValue());
			statement.setBoolean(9, employee.isRequesting());
			statement.setInt(10, employee.getEvent().getEventId());
			statement.setInt(11, employee.getEmplId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int deleteEmployee(int employeeId) {
		log.info("employee dao postgres: deleting employee");
		int rowsToDelete = 0;
		String sql = "delete from employees where employeeid = ?";
		
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

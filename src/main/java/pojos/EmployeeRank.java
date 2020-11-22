package pojos;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeRank {
	
	DEPARTMENT_HEAD(1),
	BENEFIT_COORDINATOR(2),
	OTHER(3);
	
	private int value; 
	private static Map map = new HashMap<>();
	
	private EmployeeRank(int value) {
		this.value = value;
	}
	
	static {
		for (EmployeeRank employeeRank : EmployeeRank.values()) {
			map.put(employeeRank.value, employeeRank);
		}
	}
	
	public static EmployeeRank valueOf(int employeeRank) {
		return (EmployeeRank) map.get(employeeRank);
	}
	
	public int getValue() {
		return value;
	}
}

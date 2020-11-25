package pojos;

import java.util.HashMap;
import java.util.Map;

public enum ApprovalStatus {
	PENDING(1),
	APPROVED(2),
	DENIED(3);
	
	private int value; 
	private static Map map = new HashMap<>();
	
	private ApprovalStatus(int value) {
		this.value = value;
	}
	
	static {
		for (ApprovalStatus approvalStatus : ApprovalStatus.values()) {
			map.put(approvalStatus.value, approvalStatus);
		}
	}
	
	public static ApprovalStatus valueOf(int approvalStatus) {
		return (ApprovalStatus) map.get(approvalStatus);
	}
	
	public int getValue() {
		return value;
	}
}

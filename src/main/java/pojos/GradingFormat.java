package pojos;

import java.util.HashMap;
import java.util.Map;

public enum GradingFormat {
	GPA(1),
	PERCENTAGE(2),
	LETTER(3),
	PASS_FAIL(4);
	
	private int value; 
	private static Map map = new HashMap<>();
	
	private GradingFormat(int value) {
		this.value = value;
	}
	
	static {
		for (GradingFormat gradingFormat : GradingFormat.values()) {
			map.put(gradingFormat.value, gradingFormat);
		}
	}
	
	public static GradingFormat valueOf(int gradingFormat) {
		return (GradingFormat) map.get(gradingFormat);
	}
	
	public int getValue() {
		return value;
	}
	
	
}

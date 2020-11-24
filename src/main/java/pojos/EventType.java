package pojos;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
		UNIVERSITY_COURSE(1),
		SEMINAR(2),
		CERTIFICATION_PREP_CLASS(3),
		CERTIFICATION(4),
		TECHNICAL_TRAINING(5),
		OTHER(6);
		
		private int value; 
		private static Map map = new HashMap<>();
		
		private EventType(int value) {
			this.value = value;
		}
		
		static {
			for (EventType eventType : EventType.values()) {
				map.put(eventType.value, eventType);
			}
		}
		
		public static EventType valueOf(int eventType) {
			return (EventType) map.get(eventType);
		}
		
		public int getValue() {
			return value;
		}
}

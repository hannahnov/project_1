package daos;

import java.util.List;

import pojos.EventResult;

public interface EventResultDao {
	public void createEventResult(EventResult result);
	
	public EventResult readEventResult(int requestId);
	
	public List<EventResult> readAllEventResults();
	
	public EventResult updateEventResult(int requestId, EventResult eventResult);
	
	public int deleteEventResult(int requestId);

	public String readGradeByBencoId(int bencoId);

	public byte[] readAttachmentByDepheadId(int depheadId);
}

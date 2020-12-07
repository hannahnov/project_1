package daos;

import java.util.List;

import pojos.EventResult;

public interface EventResultDao {
	public void createEventResult(EventResult result);
	
	public EventResult readEventResult(int requestId);
	
	public List<EventResult> readAllEventResults();
	
	public EventResult updateEventResult(int requestId, EventResult eventResult);
	
	public int deleteEventResult(int requestId);

	public List<EventResult> readGradeByBencoId(int bencoId);

	public byte[] readAttachmentByDepheadId(int depheadId);

	public void bencoApproveGrade(int requestId);

	public void bencoDenyGrade(int requestId);
}

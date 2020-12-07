package service;

import java.util.List;

import pojos.EventResult;

public interface EventResultService {
	
	void bencoApproveGrade(int requestId);
	
	void bencoDenyGrade(int requestId);

	List<EventResult> readResultGrade(int requestId);

	byte[] readAttachment(int requestId);

}

package service;


public interface EventResultService {
	
	void bencoApproveGrade(int requestId);
	
	void bencoDenyGrade(int requestId);

	String readResultGrade(int requestId);

	byte[] readAttachment(int requestId);

}

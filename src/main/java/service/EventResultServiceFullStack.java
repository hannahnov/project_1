package service;

import org.apache.log4j.Logger;

import daos.EventResultDao;
import daos.EventResultDaoPostgres;

public class EventResultServiceFullStack implements EventResultService {
	
	private static Logger log = Logger.getRootLogger();
	
	private EventResultDao resultDao = new EventResultDaoPostgres();

	@Override
	public String readResultGrade(int bencoId) {
		log.info("EventResult service: read event grade by benco Id");
		
		return resultDao.readGradeByBencoId(bencoId);
	}

	@Override
	public byte[] readAttachment(int depheadId) {
		log.info("EventResult service: read presentation");
		
		return resultDao.readAttachmentByDepheadId(depheadId);
	}

	@Override
	public void bencoApproveGrade(int requestId) {
		log.info("eventResult service: benco approving grade");
		resultDao.bencoApproveGrade(requestId);
		
	}

	@Override
	public void bencoDenyGrade(int requestId) {
	log.info("eventresult service: bneco denying grade");
	resultDao.bencoDenyGrade(requestId);
		
	}

}
